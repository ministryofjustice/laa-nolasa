package com.laa.nolasa.laanolasa.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.common.ReconciliationResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import com.laa.nolasa.laanolasa.util.MetricHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@XRayEnabled
public class ReconciliationService {
    @Value("${app.dry-run-mode}")
    private boolean dryRunMode;

    private InfoXServiceClient infoXServiceClient;
    private NolRepository nolRepository;
    private final MetricHandler metricHandler;

    public ReconciliationService(NolRepository nolRepository, InfoXServiceClient infoXService, MetricHandler metricHandler) {
        this.nolRepository = nolRepository;
        this.infoXServiceClient = infoXService;
        this.metricHandler = metricHandler;
    }

    @Transactional
    public void reconcile() {
        List<Nol> notInLibraEntities = nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.getStatus(), NolStatuses.LETTER_SENT.getStatus(), NolStatuses.RESULTS_REJECTED.getStatus());

        log.info("Retrieved libra {} entities from db", notInLibraEntities.size());

        notInLibraEntities.stream().map(this::reconcileNolRecord)
                                   .forEach(metricHandler::recordReconciliationResult);
    }

    public ReconciliationResult reconcileNolRecord(Nol entity) {
        Long maatId = entity.getRepOrders().getId();

        try {
            InfoXSearchResult infoXSearchResult = infoXServiceClient.search(entity);
            long numberOfResults = infoXSearchResult.getLibraIDs().size();

            if (InfoXSearchStatus.FAILURE == infoXSearchResult.getStatus()) {
                log.info("Unable to make request to infoX service for MAATID {}", maatId);
                return ReconciliationResult.ERROR;
            } else if (numberOfResults == 0) {
                log.info("No matching record returned by infoX service for MAATID {}", maatId);
                return ReconciliationResult.NO_MATCHES;
            } else if (NolStatuses.RESULTS_REJECTED.getStatus().equals(entity.getStatus())
                    && areLibraIdsEqual(entity, infoXSearchResult)) {
                log.info("Results were previously rejected, no changes are detected in libra IDs corresponding to the MAAT ID: {} ", maatId);
                return ReconciliationResult.MATCHES_ALREADY_REJECTED;
            } else {
                updateNol(entity, infoXSearchResult);
                return ReconciliationResult.fromCount(numberOfResults);
            }
        } catch (Exception e) {
            log.error("Error handling MAATID " + maatId + " - skipping", e);
            return ReconciliationResult.ERROR;
        }
    }

    void updateNol(Nol entity, InfoXSearchResult infoXSearchResult) {
        NolAutoSearchResults autoSearchResult = entity.getRepOrders().getNolAutoSearchResults();

        if (autoSearchResult == null) {
            autoSearchResult = new NolAutoSearchResults();
            autoSearchResult.setRepOrders(entity.getRepOrders());
            autoSearchResult.setSearchDate(LocalDateTime.now());
            entity.getRepOrders().setNolAutoSearchResults(autoSearchResult);
        }

        autoSearchResult.setLibraIds(infoXSearchResult.getLibraIDs());
        autoSearchResult.setSearchDate(LocalDateTime.now());

        entity.setStatus(NolStatuses.RESULTS_FOUND.getStatus());
        entity.setDateLastModified(LocalDateTime.now());
        entity.setUserLastModified("NOLASA");

        Long maatId = entity.getRepOrders().getId();
        if (dryRunMode) {
            log.info("Dry run mode - so no changes made to the database for MAAT ID {}", maatId);
        } else {
            nolRepository.save(entity);
            log.info("Status for MAAT ID {} has been updated to 'RESULTS FOUND'", maatId);
        }
    }

    boolean areLibraIdsEqual(Nol nol, InfoXSearchResult infoXSearchResult) {
        Collections.sort(nol.getRepOrders().getNolAutoSearchResults().getLibraIds());
        Collections.sort(infoXSearchResult.getLibraIDs());
        return nol.getRepOrders().getNolAutoSearchResults().getLibraIds().equals(infoXSearchResult.getLibraIDs());
    }

}

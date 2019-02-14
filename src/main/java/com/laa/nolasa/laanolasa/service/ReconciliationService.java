package com.laa.nolasa.laanolasa.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.common.ReconciliationResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResult;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import com.laa.nolasa.laanolasa.util.MetricHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
                log.info("about to update");
                updateNol(entity, infoXSearchResult);
                return ReconciliationResult.fromCount(numberOfResults);
            }
        } catch (Exception e) {
            log.error("Error handling MAATID " + maatId + " - skipping", e);
            return ReconciliationResult.ERROR;
        }
    }

    void updateNol(Nol nol, InfoXSearchResult infoXSearchResult) {

        populateAutoSearchResults(nol, infoXSearchResult);

        nol.setAutoSearchDate(LocalDateTime.now());

        nol.setStatus(NolStatuses.RESULTS_FOUND.getStatus());
        nol.setDateLastModified(LocalDateTime.now());
        nol.setUserLastModified("NOLASA");

        Long maatId = nol.getRepOrders().getId();
        if (dryRunMode) {
            log.info("Dry run mode - so no changes made to the database for MAAT ID {}", maatId);
        } else {
            log.info("Children : {}", nol.getAutoSearchResults().size());
            nolRepository.save(nol);
            log.info("Status for MAAT ID {} has been updated to 'RESULTS FOUND'", maatId);
        }
    }

    private void populateAutoSearchResults(Nol nol, InfoXSearchResult infoXSearchResult) {
        Set<Long> nolLibraIds = nol.getAutoSearchResults()
                .stream()
                .map(NolAutoSearchResult::getLibraId)
                .collect(Collectors.toSet());

        Set<Long> infoxLibraIds = new HashSet<>(infoXSearchResult.getLibraIDs());

        List<Long> removeLibraIds = nolLibraIds
                .stream()
                .filter(id -> !infoxLibraIds.contains(id))
                .collect(Collectors.toList());

        List<Long> newLibraIds = infoxLibraIds
                .stream()
                .filter(id -> !nolLibraIds.contains(id))
                .collect(Collectors.toList());

        nol.getAutoSearchResults().removeIf(r -> removeLibraIds.contains(r.getLibraId()));

        nol.getAutoSearchResults().addAll(newLibraIds.stream()
                .map(libraId -> new NolAutoSearchResult(libraId, nol)).collect(Collectors.toList()));
    }

    boolean areLibraIdsEqual(Nol nol, InfoXSearchResult infoXSearchResult) {
        if (nol.getAutoSearchResults() != null) {
            List<Long> libraIds = nol.getAutoSearchResults()
                    .stream()
                    .map(NolAutoSearchResult::getLibraId)
                    .sorted()
                    .collect(Collectors.toList());

            Collections.sort(infoXSearchResult.getLibraIDs());
            return libraIds.equals(infoXSearchResult.getLibraIDs());
        }
        return false;
    }

}

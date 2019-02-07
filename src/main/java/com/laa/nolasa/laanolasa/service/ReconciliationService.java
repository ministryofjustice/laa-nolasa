package com.laa.nolasa.laanolasa.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.laa.nolasa.laanolasa.util.LibraUtil.areLibraIDsEqual;
import static com.laa.nolasa.laanolasa.util.LibraUtil.updateLibraDetails;

@Service
@Slf4j
@XRayEnabled
public class ReconciliationService {

    @Value("${app.dry-run-mode}")
    private boolean dryRunMode;

    private InfoXServiceClient infoXServiceClient;
    private NolRepository nolRepository;
    private Counter singleMatchQueries;
    private Counter multiMatchQueries;
    private Counter noMatchQueries;
    private Counter errorQueries;
    private Counter alreadyRejectedQueries;
    private DistributionSummary numberOfResults;

    public ReconciliationService(NolRepository nolRepository, InfoXServiceClient infoXService) {
        this.nolRepository = nolRepository;
        this.infoXServiceClient = infoXService;

        // Counts of queries with each outcome
        this.singleMatchQueries = Metrics.globalRegistry.counter("queries.singleMatch");
        this.multiMatchQueries = Metrics.globalRegistry.counter("queries.multiMatch");
        this.noMatchQueries = Metrics.globalRegistry.counter("queries.noMatch");
        this.errorQueries = Metrics.globalRegistry.counter("queries.error");
        this.alreadyRejectedQueries = Metrics.globalRegistry.counter("queries.alreadyRejected");

        // The number of results returned by Libra (a number between 0 and MAX_LIBRA_RECORDS)
        this.numberOfResults = Metrics.globalRegistry.summary("reconciliation.numberOfResults");
    }

    @Transactional
    public void reconcile() {
        List<Nol> notInLibraEntities = nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf());

        log.info("Retrieved libra {} entities from db", notInLibraEntities.size());

        notInLibraEntities.stream().forEach(this::reconcileNolRecord);
    }

    private void reconcileNolRecord(Nol entity) {
        Long maatId = entity.getRepOrders().getId();

        try {
            InfoXSearchResult infoXSearchResult = infoXServiceClient.search(entity);
            int numberOfResults = infoXSearchResult.getLibraIDs().length;
            this.numberOfResults.record(numberOfResults);

            if (InfoXSearchStatus.FAILURE == infoXSearchResult.getStatus()) {
                log.info("No matching record returned by infoX service for MAATID {}", maatId);
                this.noMatchQueries.increment();
            } else if (NolStatuses.RESULTS_REJECTED.valueOf().equals(entity.getStatus()) && areLibraIDsEqual(entity.getRepOrders().getNolAutoSearchResults(), infoXSearchResult.getLibraIDs())) {
                log.info("Results were previously rejected, no changes are detected in libra IDs corresponding to the MAAT ID: {} ", maatId);
                this.alreadyRejectedQueries.increment();
            } else {
                updateNol(entity, infoXSearchResult);

                if (dryRunMode) {
                    log.info("Dry run mode - so no changes made to the database for MAAT ID {}", maatId);
                } else {
                    nolRepository.save(entity);
                    log.info("Status for MAAT ID {} has been updated to 'RESULTS FOUND'", maatId);
                }

                if (numberOfResults == 1) {
                    this.singleMatchQueries.increment();
                } else {
                    this.multiMatchQueries.increment();
                }
            }
        } catch (Exception e) {
            log.error("Error handling MAATID " + maatId + " - skipping", e);
            this.errorQueries.increment();
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

        updateLibraDetails(autoSearchResult, infoXSearchResult.getLibraIDs());
        autoSearchResult.setSearchDate(LocalDateTime.now());

        entity.setStatus(NolStatuses.RESULTS_FOUND.valueOf());
        entity.setDateLastModified(LocalDateTime.now());
        entity.setUserLastModified("NOLASA");
    }

}

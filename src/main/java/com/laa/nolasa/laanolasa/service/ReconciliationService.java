package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.laa.nolasa.laanolasa.util.LibraUtil.isLibraIDsEqual;
import static com.laa.nolasa.laanolasa.util.LibraUtil.updateLibraDetails;

@Service
@Slf4j
//@XRayEnabled
public class ReconciliationService {

    private InfoXServiceClient infoXServiceClient;
    private NolRepository nolRepository;

    public ReconciliationService(NolRepository nolRepository, InfoXServiceClient infoXService) {
        this.nolRepository = nolRepository;
        this.infoXServiceClient = infoXService;
    }

    @Transactional
    public void reconcile() {
        List<Nol> notInLibraEntities = nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf());

        log.info("Retrieved libra {} entities from db", notInLibraEntities.size());

        notInLibraEntities.stream().forEach(entity -> {

            InfoXSearchResult infoXSearchResult = infoXServiceClient.search(entity);

            if (InfoXSearchStatus.FAILURE == infoXSearchResult.getStatus()) {
                log.debug("No matching record returned by infoX service for MAATID {}", entity.getRepOrders().getId());
            } else if (NolStatuses.RESULTS_REJECTED.valueOf().equals(entity.getStatus()) && isLibraIDsEqual(entity.getRepOrders().getNolAutoSearchResults(), infoXSearchResult.getLibraIDs())) {
                log.info("No changes were detected in libra IDs corresponding to the MAAT ID {} ", entity.getRepOrders().getId());
            } else {
                updateNol(entity, infoXSearchResult);
            }
        });
    }

    private void updateNol(Nol entity, InfoXSearchResult infoXSearchResult) {
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
        nolRepository.save(entity);
        log.info("Nol table updated for entity with MAAT ID {} ", entity.getRepOrders().getId());

    }

}

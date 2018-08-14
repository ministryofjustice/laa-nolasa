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
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ReconciliationService {

    private final InfoXService infoXService;
    private NolRepository nolRepository;

    public ReconciliationService(NolRepository nolRepository, InfoXService infoXService) {
        this.nolRepository = nolRepository;
        this.infoXService = infoXService;
    }

    @Transactional
    public void reconcile() {
        List<Nol> notInLibraEntities = nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf());

        log.info("Retrieved libra {} entities from db", notInLibraEntities.size());

        notInLibraEntities.stream().forEach(entity -> {

            InfoXSearchResult infoXSearchResult = infoXService.search(entity);

            if (InfoXSearchStatus.SUCCESS == infoXSearchResult.getStatus()) {

                updateNol(entity, infoXSearchResult);

            } else {

                log.debug("No matching record returned by infoX service for MAATID {}", entity.getRepOrders().getId());
            }
        });
    }

    private void updateNol(Nol entity, InfoXSearchResult infoXSearchResult) {
        NolAutoSearchResults autoSearchResult = entity.getRepOrders().getNolAutoSearchResults();
        updateLibraDetails(autoSearchResult, infoXSearchResult.getLibraIDs());
        autoSearchResult.setSearchDate(LocalDate.now());

        entity.setStatus(NolStatuses.RESULTS_FOUND.valueOf());
        nolRepository.save(entity);
        log.info("Nol table updated for entity with MAAT ID {} ", entity.getRepOrders().getId());
    }

    private void updateLibraDetails(NolAutoSearchResults autoSearchResult, Long[] libraIDs) {

        autoSearchResult.setLibrId1(libraIDs[0]);
        autoSearchResult.setLibrId2(libraIDs[1]);
        autoSearchResult.setLibrId3(libraIDs[2]);
        autoSearchResult.setLibrId4(libraIDs[3]);
        autoSearchResult.setLibrId5(libraIDs[4]);
        autoSearchResult.setLibrId6(libraIDs[5]);
        autoSearchResult.setLibrId7(libraIDs[6]);
        autoSearchResult.setLibrId8(libraIDs[7]);
        autoSearchResult.setLibrId9(libraIDs[8]);
        autoSearchResult.setLibrId10(libraIDs[9]);
        autoSearchResult.setLibrId11(libraIDs[10]);
        autoSearchResult.setLibrId12(libraIDs[11]);
        autoSearchResult.setLibrId13(libraIDs[12]);
        autoSearchResult.setLibrId14(libraIDs[13]);
        autoSearchResult.setLibrId15(libraIDs[14]);
    }


}

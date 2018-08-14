package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public void reconcile() {
        List<Nol> notInLibraEntities = nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf());

        log.info("NOL result {}", notInLibraEntities.size());

        notInLibraEntities.stream().forEach(entity -> {

            InfoXSearchResult infoXSearchResult = infoXService.search(entity);

            if (InfoXSearchStatus.SUCCESS == infoXSearchResult.getStatus()) {

            }
        });
    }


}

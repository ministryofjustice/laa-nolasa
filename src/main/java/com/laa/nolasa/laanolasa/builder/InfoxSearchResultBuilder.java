package com.laa.nolasa.laanolasa.builder;

import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice._2013._11.magistrates.LibraSearchResponse;

@Component
@Slf4j
public class InfoxSearchResultBuilder {

    private static final int MAX_LIBRA_RECORDS = 15;

    public InfoXSearchResult buildInfoXSearchResult(LibraSearchResponse libraSearchResponse) {

        log.info("Libra Response code:{}", libraSearchResponse.getAckResponse().getException().getERRORCODE());
        switch (libraSearchResponse.getAckResponse().getException().getERRORCODE()) {
            case "100100":
                log.info("{} matches found by Libra, only storing 15", libraSearchResponse.getSearchResultItem().size());
                return getInfoXSearchResult(libraSearchResponse);
            case "1":
                log.info("{} matches found by Libra", libraSearchResponse.getSearchResultItem().size());
                return getInfoXSearchResult(libraSearchResponse);
            case "999999":
                return new InfoXSearchResult(InfoXSearchStatus.FAILURE);
            default:
                return new InfoXSearchResult(InfoXSearchStatus.FAILURE);
        }

    }

    InfoXSearchResult getInfoXSearchResult(LibraSearchResponse libraSearchResponse) {
        Long result[] = new Long[15];
        for (int i = 0; (i < libraSearchResponse.getSearchResultItem().size() && i < MAX_LIBRA_RECORDS); i++) {
            result[i] = Long.valueOf(libraSearchResponse.getSearchResultItem().get(i).getCaseResult().get(0).getCaseDetail().getLibraCaseId());
        }
        return new InfoXSearchResult(result, InfoXSearchStatus.SUCCESS);
    }
}
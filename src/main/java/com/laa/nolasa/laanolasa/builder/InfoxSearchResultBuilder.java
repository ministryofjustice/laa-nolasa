package com.laa.nolasa.laanolasa.builder;

import com.laa.nolasa.laanolasa.common.InfoxStatus;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice._2013._11.magistrates.LibraSearchResponse;

import java.util.List;
import java.util.stream.Collectors;

import static com.laa.nolasa.laanolasa.dto.InfoXSearchResult.MAX_LIBRA_RECORDS;

@Component
@Slf4j
public class InfoxSearchResultBuilder {

    public InfoXSearchResult buildInfoXSearchResult(LibraSearchResponse libraSearchResponse) {

        InfoxStatus infoxStatus = InfoxStatus.fromString(libraSearchResponse.getAckResponse().getException().getERRORCODE());

        log.info("Libra Response code:{}", infoxStatus);
        switch (infoxStatus) {
            case LIBRA_GREATER_THAN_15_CODE:
                log.info("{} matches found by Libra, only storing {}", libraSearchResponse.getSearchResultItem().size(), MAX_LIBRA_RECORDS);
                return getInfoXSearchResult(libraSearchResponse);
            case LIBRA_SUCCESS_CODE:
                log.info("{} matches found by Libra", libraSearchResponse.getSearchResultItem().size());
                return getInfoXSearchResult(libraSearchResponse);
            case LIBRA_FAILED_EXCEPTION:
            case LIBRA_INVALID_CODE:
            default:
                return new InfoXSearchResult(InfoXSearchStatus.FAILURE);
        }
    }

    InfoXSearchResult getInfoXSearchResult(LibraSearchResponse libraSearchResponse) {

        List<Long> results  = libraSearchResponse.getSearchResultItem().stream()
                .limit(MAX_LIBRA_RECORDS)
                .map(rs -> rs.getCaseResult().stream()
                        .findFirst().get()
                        .getCaseDetail()
                        .getLibraCaseId())
                .map(Long::valueOf).collect(Collectors.toList());

        return new InfoXSearchResult(results, InfoXSearchStatus.SUCCESS);
    }
}
package com.laa.nolasa.laanolasa.builder;

import com.laa.nolasa.laanolasa.common.InfoxStatus;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice._2013._11.magistrates.LibraSearchResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InfoxSearchResultBuilder {

    public InfoXSearchResult buildInfoXSearchResult(LibraSearchResponse libraSearchResponse) {

        String libraResponseErrorCode = libraSearchResponse.getAckResponse().getException().getERRORCODE();
        InfoxStatus infoxStatus = InfoxStatus.fromString(libraResponseErrorCode);

        log.info("Libra Response Infox Status: {}, Error Code: {}", infoxStatus, libraResponseErrorCode);
        switch (infoxStatus) {
            case LIBRA_GREATER_THAN_15_CODE:
            case LIBRA_SUCCESS_CODE:
            case LIBRA_NO_MATCH_FOUND:
                return getInfoXSearchResult(libraSearchResponse);
            case LIBRA_FAILED_EXCEPTION:
            case LIBRA_INVALID_CODE:
            default:
                return new InfoXSearchResult(InfoXSearchStatus.FAILURE);
        }
    }

    InfoXSearchResult getInfoXSearchResult(LibraSearchResponse libraSearchResponse) {

        List<Long> results  = libraSearchResponse.getSearchResultItem().stream()
                .map(rs -> rs.getCaseResult().stream()
                        .findFirst().get()
                        .getCaseDetail()
                        .getLibraCaseId())
                .map(Long::valueOf).collect(Collectors.toList());

        return new InfoXSearchResult(results, InfoXSearchStatus.SUCCESS);
    }
}
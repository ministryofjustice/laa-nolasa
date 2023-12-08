package com.laa.nolasa.laanolasa.builder;

import com.laa.nolasa.laanolasa.common.InfoxStatus;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.justice._2013._11.magistrates.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoxSearchResultBuilderTest {


    private InfoxSearchResultBuilder infoxSearchResultBuilder;

    @BeforeEach
    public void setUp() {
        infoxSearchResultBuilder = new InfoxSearchResultBuilder();
    }

    @Test
    public void shouldBuildInfoXSearchResultSucceedWhenStatusIsGreaterThan15Code() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        libraSearchResponse.getAckResponse().getException().setERRORCODE(InfoxStatus.LIBRA_GREATER_THAN_15_CODE.getCode());
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.SUCCESS, infoXSearchResult.getStatus());
    }
    @Test
    public void shouldBuildInfoXSearchResultSucceedWhenStatusIsSuccessCode() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        libraSearchResponse.getAckResponse().getException().setERRORCODE(InfoxStatus.LIBRA_SUCCESS_CODE.getCode());
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.SUCCESS, infoXSearchResult.getStatus());
    }
    @Test
    public void shouldBuildInfoXSearchResultFailWhenStatusIsLibraFailedException() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        libraSearchResponse.getAckResponse().getException().setERRORCODE(InfoxStatus.LIBRA_FAILED_EXCEPTION.getCode());
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.FAILURE, infoXSearchResult.getStatus());
    }

    @Test
    public void shouldBuildInfoXSearchResultFailWhenStatusIsNoMatchFound() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        libraSearchResponse.getAckResponse().getException().setERRORCODE(InfoxStatus.LIBRA_NO_MATCH_FOUND.getCode());
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.SUCCESS, infoXSearchResult.getStatus());
    }

    @Test
    public void shouldGetInfoXSearchResultSucceed() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.getInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.SUCCESS, infoXSearchResult.getStatus());
    }

    private LibraSearchResponse getLibraSearchResponse() {
        LibraSearchResponse libraSearchResponse = new LibraSearchResponse();
        AckResponse ackResponse = new AckResponse();
        LibraExceptionType exception = new LibraExceptionType();
        exception.setERRORCODE("1");
        ackResponse.setException(exception);
        libraSearchResponse.setAckResponse(ackResponse);

        ResultItem resultItem = new ResultItem();
        libraSearchResponse.getSearchResultItem().add(resultItem);
        LibraCaseSessionType caseResult = new LibraCaseSessionType();

        resultItem.getCaseResult().add(caseResult);

        LibraCaseType caseDetail = new LibraCaseType();
        caseDetail.setLibraCaseId("1000");
        caseResult.setCaseDetail(caseDetail);
        return libraSearchResponse;
    }
}
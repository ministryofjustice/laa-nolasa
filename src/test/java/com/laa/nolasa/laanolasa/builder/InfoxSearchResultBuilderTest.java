package com.laa.nolasa.laanolasa.builder;

import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import org.junit.Before;
import org.junit.Test;
import uk.gov.justice._2013._11.magistrates.*;

import static org.junit.Assert.*;

public class InfoxSearchResultBuilderTest {


    private InfoxSearchResultBuilder infoxSearchResultBuilder;

    @Before
    public void setUp() throws Exception {
        infoxSearchResultBuilder = new InfoxSearchResultBuilder();
    }

    @Test
    public void shouldBuildInfoXSearchResultSucceedWhenStatusIs100100() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        libraSearchResponse.getAckResponse().getException().setERRORCODE("100100");
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.SUCCESS, infoXSearchResult.getStatus());
    }
    @Test
    public void shouldBuildInfoXSearchResultSucceedWhenStatusIs1() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        libraSearchResponse.getAckResponse().getException().setERRORCODE("1");
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.SUCCESS, infoXSearchResult.getStatus());
    }
    @Test
    public void shouldBuildInfoXSearchResultFailWhenStatusIs999999() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        libraSearchResponse.getAckResponse().getException().setERRORCODE("999999");
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.FAILURE, infoXSearchResult.getStatus());
    }

    @Test
    public void shouldBuildInfoXSearchResultFailWhenStatusIs100000() {
        LibraSearchResponse libraSearchResponse = getLibraSearchResponse();
        libraSearchResponse.getAckResponse().getException().setERRORCODE("100000");
        InfoXSearchResult infoXSearchResult = infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
        assertEquals(InfoXSearchStatus.FAILURE, infoXSearchResult.getStatus());
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
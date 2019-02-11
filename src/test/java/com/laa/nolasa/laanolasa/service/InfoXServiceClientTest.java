package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.builder.InfoxSearchResultBuilder;
import com.laa.nolasa.laanolasa.builder.LibraSearchRequestBuilder;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.util.MetricHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import uk.gov.justice._2013._11.magistrates.LibraSearchRequest;
import uk.gov.justice._2013._11.magistrates.LibraSearchResponse;

import javax.xml.datatype.DatatypeConfigurationException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InfoXServiceClientTest {

    @Mock
    private LibraSearchRequestBuilder libraSearchRequestBuilder;

    @Mock
    private InfoxSearchResultBuilder infoxSearchResultBuilder;

    @Mock
    private WebServiceTemplate webServiceTemplate;

    @Mock
    private MetricHandler metricHandler;

    private InfoXServiceClient infoXServiceClient;

    @Before
    public void setUp() {
        infoXServiceClient = new InfoXServiceClient(libraSearchRequestBuilder, infoxSearchResultBuilder, webServiceTemplate, metricHandler);
    }

    @Test
    public void shouldSucceedSearch() throws DatatypeConfigurationException {

        Nol nol = new Nol();
        LibraSearchRequest libraSearchRequest = new LibraSearchRequest();
        LibraSearchResponse libraSearchResponse = new LibraSearchResponse();

        when(libraSearchRequestBuilder.buildLibraSearchRequest(nol)).thenReturn(libraSearchRequest);
        when(webServiceTemplate.marshalSendAndReceive(libraSearchRequest)).thenReturn(libraSearchResponse);
        when(infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse)).thenReturn(any(InfoXSearchResult.class));

        infoXServiceClient.search(nol);

        verify(libraSearchRequestBuilder).buildLibraSearchRequest(nol);
        verify(webServiceTemplate).marshalSendAndReceive(libraSearchRequest);
        verify(infoxSearchResultBuilder).buildInfoXSearchResult(libraSearchResponse);
    }

    @Test
    public void shouldSearchThrowException() throws DatatypeConfigurationException {

        Nol nol = new Nol();

        when(libraSearchRequestBuilder.buildLibraSearchRequest(nol)).thenThrow(DatatypeConfigurationException.class);

        InfoXSearchResult infoXSearchResult = infoXServiceClient.search(nol);

        assertEquals(InfoXSearchStatus.FAILURE, infoXSearchResult.getStatus());
    }
}
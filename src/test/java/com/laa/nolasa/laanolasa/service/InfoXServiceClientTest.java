package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.builder.InfoxSearchResultBuilder;
import com.laa.nolasa.laanolasa.builder.LibraSearchRequestBuilder;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.util.MetricHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice._2013._11.magistrates.LIBRAServicePortType;
import uk.gov.justice._2013._11.magistrates.LibraSearchRequest;
import uk.gov.justice._2013._11.magistrates.LibraSearchResponse;

import javax.xml.datatype.DatatypeConfigurationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InfoXServiceClientTest {

    @Mock
    private LibraSearchRequestBuilder libraSearchRequestBuilder;

    @Mock
    private InfoxSearchResultBuilder infoxSearchResultBuilder;

    @Mock
    private LIBRAServicePortType infoxProxy;

    @Mock
    private MetricHandler metricHandler;

    private InfoXServiceClient infoXServiceClient;

    @BeforeEach
    public void setUp() {
        infoXServiceClient = new InfoXServiceClient(libraSearchRequestBuilder, infoxSearchResultBuilder, infoxProxy, metricHandler);
    }

    @Test
    public void shouldSucceedSearch() throws DatatypeConfigurationException {

        Nol nol = new Nol();
        LibraSearchRequest libraSearchRequest = new LibraSearchRequest();
        LibraSearchResponse libraSearchResponse = new LibraSearchResponse();

        when(libraSearchRequestBuilder.buildLibraSearchRequest(nol)).thenReturn(libraSearchRequest);
        when(infoxProxy.libraSearch(libraSearchRequest)).thenReturn(libraSearchResponse);
        when(infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse)).thenReturn(any(InfoXSearchResult.class));

        infoXServiceClient.search(nol);

        verify(libraSearchRequestBuilder).buildLibraSearchRequest(nol);
        verify(infoxProxy).libraSearch(libraSearchRequest);
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
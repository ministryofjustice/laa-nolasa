package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.builder.InfoxSearchResultBuilder;
import com.laa.nolasa.laanolasa.builder.LibraSearchRequestBuilder;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.util.MetricHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import uk.gov.justice._2013._11.magistrates.LibraSearchResponse;

import javax.xml.datatype.DatatypeConfigurationException;

@Service
@Slf4j
public class InfoXServiceClient {

    private final MetricHandler metricHandler;
    private LibraSearchRequestBuilder libraSearchRequestBuilder;
    private InfoxSearchResultBuilder infoxSearchResultBuilder;
    private WebServiceTemplate webServiceTemplate;


    public InfoXServiceClient(LibraSearchRequestBuilder libraSearchRequestBuilder, InfoxSearchResultBuilder infoxSearchResultBuilder, WebServiceTemplate webServiceTemplate, MetricHandler metricHandler) {
        this.libraSearchRequestBuilder = libraSearchRequestBuilder;
        this.infoxSearchResultBuilder = infoxSearchResultBuilder;
        this.webServiceTemplate = webServiceTemplate;
        this.metricHandler = metricHandler;
    }

    public InfoXSearchResult search(Nol nol) {
        LibraSearchResponse libraSearchResponse;
        try {
            libraSearchResponse = (LibraSearchResponse) webServiceTemplate.marshalSendAndReceive(libraSearchRequestBuilder.buildLibraSearchRequest(nol));
        } catch (DatatypeConfigurationException e) {
            log.error("Exception thrown while populating Libra Search Request", e);
            return new InfoXSearchResult(InfoXSearchStatus.FAILURE);
        }

        int numberOfResults = libraSearchResponse.getSearchResultItem().size();
        this.metricHandler.recordNumberOfResults(numberOfResults);

        return infoxSearchResultBuilder.buildInfoXSearchResult(libraSearchResponse);
    }

}
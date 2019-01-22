package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import uk.gov.justice._2013._11.magistrates.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.GregorianCalendar;

@Service
@Slf4j
public class InfoXServiceClient {

    private static final int MAX_LIBRA_RECORDS = 15;

    @Autowired
    private WebServiceTemplate webServiceTemplate;


    public InfoXSearchResult search(Nol nol) {
        LibraSearchResponse libraSearchResponse;
        try {
            libraSearchResponse = (LibraSearchResponse) webServiceTemplate.marshalSendAndReceive(buildLibraSearchRequest(nol));
        } catch (DatatypeConfigurationException e) {
            log.error("Exception thrown while populating Libra Search Request", e);
            return new InfoXSearchResult(new Long[15], InfoXSearchStatus.FAILURE);
        }
        return buildInfoXSearchResult(libraSearchResponse);
    }

    private LibraSearchRequest buildLibraSearchRequest(Nol nol) throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        LibraSearchRequest libraSearchRequest = objectFactory.createLibraSearchRequest();

        LibraCriteriaType criteria = objectFactory.createLibraCriteriaType();

        libraSearchRequest.setCriteria(criteria);

        criteria.setSearchType(0);
        criteria.setSearchPattern(5);
        criteria.setSurname(nol.getRepOrders().getApplicants().getLastName());
        criteria.setCJSAreaCode(nol.getRepOrders().getMagistrateCourts().getCjsAreaCode());

        if (null != nol.getRepOrders().getHearingDate()) {
            criteria.setDateOfHearing(getGregorianCalendar(nol.getRepOrders().getHearingDate()));
        }

        return libraSearchRequest;

    }

    private XMLGregorianCalendar getGregorianCalendar(LocalDateTime date) throws DatatypeConfigurationException {
        GregorianCalendar gcal = GregorianCalendar.from(ZonedDateTime.of(date, ZoneId.systemDefault()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
    }

    private InfoXSearchResult buildInfoXSearchResult(LibraSearchResponse libraSearchResponse) {
        Long result[] = new Long[15];

        log.info("Response code:{}", libraSearchResponse.getAckResponse().getException().getERRORCODE());
        switch (libraSearchResponse.getAckResponse().getException().getERRORCODE()) {
            case "1":
            case "100100":
                log.info("Libra id's:{}", libraSearchResponse.getSearchResultItem().size());
                for (int i = 0; (i < libraSearchResponse.getSearchResultItem().size() && i < MAX_LIBRA_RECORDS); i++) {
                    result[i] = Long.valueOf(libraSearchResponse.getSearchResultItem().get(i).getCaseResult().get(0).getCaseDetail().getLibraCaseId());
                }
                return new InfoXSearchResult(result, InfoXSearchStatus.SUCCESS);
            case "999999":
                return new InfoXSearchResult(result, InfoXSearchStatus.FAILURE);
            default:
                return new InfoXSearchResult(result, InfoXSearchStatus.FAILURE);
        }

    }
}
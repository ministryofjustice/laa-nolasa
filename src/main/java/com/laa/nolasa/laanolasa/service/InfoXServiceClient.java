package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.common.InfoxErrors;
import com.laa.nolasa.laanolasa.common.exception.InfoXServiceException;
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
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

@Service
@Slf4j
public class InfoXServiceClient {

    private static final int MAX_LIBRA_RECORDS = 15;

    @Autowired
    private WebServiceTemplate webServiceTemplate;

//
//    public InfoXSearchResult search(Nol nol) {
//        LibraSearchResponse libraSearchResponse;
//        try {
//            libraSearchResponse = (LibraSearchResponse) webServiceTemplate.marshalSendAndReceive(buildLibraSearchRequest(nol));
//        } catch (DatatypeConfigurationException e) {
//            log.error("Exception thrown while populating Libra Search Request", e);
//            return  new InfoXSearchResult(new Long[15], InfoXSearchStatus.FAILURE);
//        }
//        return buildInfoXSearchResult(libraSearchResponse);
//    }

    public InfoXSearchResult search(Nol nol) {
        Long result[] = new Long[15];
        if (nol.getRepOrders().getId().equals(new Long(5631971))) {
            result[0] = new Long(100);
            return new InfoXSearchResult(result, InfoXSearchStatus.SUCCESS);
        }
        if (nol.getRepOrders().getId().equals(new Long(5631968))) {
            result[0] = new Long(200);
            result[1] = new Long(300);
            return new InfoXSearchResult(result, InfoXSearchStatus.SUCCESS);
        }
        if (nol.getRepOrders().getId().equals(new Long(5631977))) {
            result[0] = new Long(400);
            result[1] = new Long(500);
            return new InfoXSearchResult(result, InfoXSearchStatus.SUCCESS);
        }
        return new InfoXSearchResult(result, InfoXSearchStatus.FAILURE);
    }

    private LibraSearchRequest buildLibraSearchRequest(Nol nol) throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        LibraSearchRequest libraSearchRequest = objectFactory.createLibraSearchRequest();

        libraSearchRequest.getCriteria().setSearchType(0);
        libraSearchRequest.getCriteria().setSearchPattern(5);
        libraSearchRequest.getCriteria().setSurname(nol.getRepOrders().getApplicants().getLastName());
        libraSearchRequest.getCriteria().setCJSAreaCode(nol.getRepOrders().getMagistrateCourts().getCjsAreaCode());

        if (null != nol.getRepOrders().getHearingDate()) {
            libraSearchRequest.getCriteria().setDateOfHearing(getGregorianCalendar(nol.getRepOrders().getHearingDate()));
        }

        return libraSearchRequest;

    }

    private XMLGregorianCalendar getGregorianCalendar(LocalDateTime date) throws DatatypeConfigurationException {
        GregorianCalendar gcal = GregorianCalendar.from(ZonedDateTime.of(date, ZoneId.systemDefault()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
    }

    private InfoXSearchResult buildInfoXSearchResult(LibraSearchResponse libraSearchResponse) {
        Long result[] = new Long[15];

        switch (libraSearchResponse.getAckResponse().getException().getERRORCODE()) {
            case "1":
            case "100100":
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

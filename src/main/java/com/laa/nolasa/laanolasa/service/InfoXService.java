package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.common.InfoxErrors;
import com.laa.nolasa.laanolasa.common.exception.InfoXServiceException;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import org.springframework.stereotype.Service;
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
public class InfoXService {

    private static final int MAX_LIBRA_RECORDS = 15;

    private LIBRAServicePortType libraServicePortType;

    private ObjectFactory objectFactory;

    public InfoXService(LIBRAServicePortType libraServicePortType, ObjectFactory objectFactory) {
        this.libraServicePortType = libraServicePortType;
        this.objectFactory = objectFactory;
    }


    public InfoXSearchResult search(Nol nol) {
        LibraSearchResponse libraSearchResponse;
        try {
            libraSearchResponse = libraServicePortType.libraSearch(buildLibraSearchRequest(nol));
        } catch (DatatypeConfigurationException e) {
            throw new InfoXServiceException();
        }
        return buildInfoXSearchResult(libraSearchResponse);
    }

    private LibraSearchRequest buildLibraSearchRequest(Nol nol) throws DatatypeConfigurationException {
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

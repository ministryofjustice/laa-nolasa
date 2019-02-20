package com.laa.nolasa.laanolasa.builder;

import com.laa.nolasa.laanolasa.entity.Nol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice._2013._11.magistrates.LibraCriteriaType;
import uk.gov.justice._2013._11.magistrates.LibraSearchRequest;
import uk.gov.justice._2013._11.magistrates.ObjectFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

@Component
@Slf4j
public class LibraSearchRequestBuilder {

    public LibraSearchRequest buildLibraSearchRequest(Nol nol) throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        LibraSearchRequest libraSearchRequest = objectFactory.createLibraSearchRequest();

        LibraCriteriaType criteria = objectFactory.createLibraCriteriaType();

        libraSearchRequest.setCriteria(criteria);

        criteria.setSearchType(0);
        criteria.setSearchPattern(5);
        criteria.setSurname(nol.getRepOrders().getApplicants().getLastName());
        criteria.setCJSAreaCode(nol.getRepOrders().getMagistrateCourts().getCjsAreaCode());

        if (Optional.of(nol.getRepOrders().getHearingDate()).isPresent()) {
            criteria.setDateOfHearing(getGregorianCalendar(nol.getRepOrders().getHearingDate()));
        }

        return libraSearchRequest;

    }

    XMLGregorianCalendar getGregorianCalendar(LocalDateTime date) throws DatatypeConfigurationException {
        GregorianCalendar gcal = GregorianCalendar.from(ZonedDateTime.of(date, ZoneId.systemDefault()));

        XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        xmlCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

        return xmlCal;
    }
}
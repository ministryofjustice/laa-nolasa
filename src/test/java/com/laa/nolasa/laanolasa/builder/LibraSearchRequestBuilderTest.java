package com.laa.nolasa.laanolasa.builder;

import com.laa.nolasa.laanolasa.entity.Applicants;
import com.laa.nolasa.laanolasa.entity.MagistrateCourts;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.RepOrders;
import org.junit.Before;
import org.junit.Test;
import uk.gov.justice._2013._11.magistrates.LibraSearchRequest;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class LibraSearchRequestBuilderTest {


    private LibraSearchRequestBuilder libraSearchRequestBuilder;

    @Before
    public void setUp() throws Exception {
        libraSearchRequestBuilder = new LibraSearchRequestBuilder();
    }

    @Test
    public void shouldBuildLibraSearchRequestSucceed() throws DatatypeConfigurationException {
        Nol nol = new Nol();
        RepOrders repOrders = new RepOrders();
        Applicants applicants = new Applicants();
        MagistrateCourts magistrateCourts = new MagistrateCourts();

        magistrateCourts.setCjsAreaCode("9");
        repOrders.setMagistrateCourts(magistrateCourts);
        applicants.setLastName("last name");
        repOrders.setApplicants(applicants);
        repOrders.setHearingDate( LocalDateTime.now());

        nol.setRepOrders(repOrders);

        LibraSearchRequest libraSearchRequest = libraSearchRequestBuilder.buildLibraSearchRequest(nol);

        assertEquals("last name", libraSearchRequest.getCriteria().getSurname());
    }
}
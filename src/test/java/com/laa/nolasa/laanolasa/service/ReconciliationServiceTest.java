package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;
import com.laa.nolasa.laanolasa.entity.RepOrders;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import com.laa.nolasa.laanolasa.util.MetricHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReconciliationServiceTest {

    @Mock
    private InfoXServiceClient infoXServiceClient;

    @Mock
    private NolRepository nolRepository;

    @Mock
    private MetricHandler metricHandler;

    private ReconciliationService reconciliationService;

    @Before
    public void setUp() throws Exception {
        reconciliationService = new ReconciliationService(nolRepository, infoXServiceClient, metricHandler);
    }

    @Test
    public void shouldReconcileLibraIDs() {

        List<Nol> nols = new ArrayList<Nol>();

        NolAutoSearchResults autoSearch1 = getNolAutoSearchResults(0L);

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(NolStatuses.RESULTS_REJECTED.valueOf());

        NolAutoSearchResults autoSearch2 = getNolAutoSearchResults(20L);

        RepOrders repoOrder2 = new RepOrders();
        repoOrder2.setId(920L);
        repoOrder2.setNolAutoSearchResults(autoSearch2);

        Nol nol2 = new Nol();
        nol2.setRepOrders(repoOrder2);

        Nol nol3 = new Nol();
        RepOrders repoOrder3 = new RepOrders();
        repoOrder3.setId(920L);
        nol3.setRepOrders(repoOrder3);

        nols.add(nol1);
        nols.add(nol2);
        nols.add(nol3);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf())).thenReturn(nols);

        Long[] libraIDs1 = getLibraIds(0L);

        InfoXSearchStatus status1 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(libraIDs1, status1);

        Long[] libraIDs2 = getLibraIds(30L);


        InfoXSearchStatus status2 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult2 = new InfoXSearchResult(libraIDs2, status2);

        InfoXSearchStatus status3 = InfoXSearchStatus.FAILURE;
        InfoXSearchResult infoXSearchResult3 = new InfoXSearchResult(new Long[15], status3);

        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1).thenReturn(infoXSearchResult2).thenReturn(infoXSearchResult3);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf());
        verify(infoXServiceClient).search(nol1);
        verify(infoXServiceClient).search(nol2);
        verify(nolRepository).save(nol2);
        verify(nolRepository, never()).save(nol1);
    }


    @Test
    public void shouldNotProcessRejectedResultsWhenLibraIdsMatch() {

        List<Nol> nols = new ArrayList<Nol>();

        NolAutoSearchResults autoSearch1 = getNolAutoSearchResults(0L);

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(NolStatuses.RESULTS_REJECTED.valueOf());


        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf())).thenReturn(nols);

        Long[] libraIDs1 = getLibraIds(0L);

        InfoXSearchStatus status1 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(libraIDs1, status1);


        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf());
        verify(infoXServiceClient).search(nol1);
        verify(nolRepository, never()).save(nol1);
    }

    @Test
    public void shoulProcessRejectedResultsWhenLibraIdsDoNotMatch() {

        List<Nol> nols = new ArrayList<Nol>();

        NolAutoSearchResults autoSearch1 = getNolAutoSearchResults(0L);

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(NolStatuses.RESULTS_REJECTED.valueOf());


        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf())).thenReturn(nols);

        Long[] libraIDs1 = getLibraIds(0L);

        libraIDs1[10] = 16L;

        InfoXSearchStatus status1 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(libraIDs1, status1);


        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf());
        verify(infoXServiceClient).search(nol1);
        verify(nolRepository).save(nol1);
    }

    @Test
    public void shouldNotSaveNolInDryRunMode() {

        List<Nol> nols = new ArrayList<Nol>();

        NolAutoSearchResults autoSearch1 = getNolAutoSearchResults(0L);

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf())).thenReturn(nols);

        Long[] libraIDs1 = getLibraIds(0L);
        libraIDs1[10] = 16L;

        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(libraIDs1, InfoXSearchStatus.SUCCESS);


        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        ReflectionTestUtils.setField(reconciliationService, "dryRunMode", true);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf());
        verify(infoXServiceClient).search(nol1);
        verify(nolRepository, never()).save(nol1);
    }

    @Test
    public void shouldUpdateNolSucceedWhenNoPreviousAutoSearchResult() {

        NolAutoSearchResults autoSearch1 = null;

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);

        Long[] libraIDs1 = getLibraIds(0L);

        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(libraIDs1, InfoXSearchStatus.SUCCESS);

        reconciliationService.updateNol(nol1, infoXSearchResult1);
        assertEquals(NolStatuses.RESULTS_FOUND.valueOf(),  nol1.getStatus());
    }

    private NolAutoSearchResults getNolAutoSearchResults(Long startValue) {
        NolAutoSearchResults autoSearch1 = new NolAutoSearchResults();
        autoSearch1.setLibrId1(startValue + 1L);
        autoSearch1.setLibrId2(startValue + 2L);
        autoSearch1.setLibrId3(startValue + 3L);
        autoSearch1.setLibrId4(startValue + 4L);
        autoSearch1.setLibrId5(startValue + 5L);
        autoSearch1.setLibrId6(startValue + 6L);
        autoSearch1.setLibrId7(startValue + 7L);
        autoSearch1.setLibrId8(startValue + 8L);
        autoSearch1.setLibrId9(startValue + 9L);
        autoSearch1.setLibrId10(startValue + 10L);
        autoSearch1.setLibrId11(startValue + 11L);
        autoSearch1.setLibrId12(startValue + 12L);
        autoSearch1.setLibrId13(startValue + 13L);
        autoSearch1.setLibrId14(startValue + 14L);
        autoSearch1.setLibrId15(startValue + 15L);
        return autoSearch1;
    }

    private Long[] getLibraIds(Long startValue) {
        Long[] libraIDs1 = new Long[15];
        libraIDs1[0] = startValue + 1L;
        libraIDs1[1] = startValue + 2L;
        libraIDs1[2] = startValue + 3L;
        libraIDs1[3] = startValue + 4L;
        libraIDs1[4] = startValue + 5L;
        libraIDs1[5] = startValue + 6L;
        libraIDs1[6] = startValue + 7L;
        libraIDs1[7] = startValue + 8L;
        libraIDs1[8] = startValue + 9L;
        libraIDs1[9] = startValue + 10L;
        libraIDs1[10] = startValue + 11L;
        libraIDs1[11] = startValue + 12L;
        libraIDs1[12] = startValue + 13L;
        libraIDs1[13] = startValue + 14L;
        libraIDs1[14] = startValue + 15L;
        return libraIDs1;
    }

}
package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.common.ReconciliationResult;
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
import java.util.Arrays;
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
    public void setUp()  {
        reconciliationService = new ReconciliationService(nolRepository, infoXServiceClient, metricHandler);
    }

    @Test
    public void shouldReconcileLibraIDs() {

        List<Nol> nols = new ArrayList<>();

        NolAutoSearchResults autoSearch1 = new NolAutoSearchResults();
        autoSearch1.setLibraIds(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L));

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(NolStatuses.RESULTS_REJECTED.valueOf());

        NolAutoSearchResults autoSearch2 = new NolAutoSearchResults();
        autoSearch2.setLibraIds(Arrays.asList(21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L, 30L, 31L, 32L, 33L, 34L, 35L));

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

        List<Long> libraIDs1 = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L);
        InfoXSearchStatus status1 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(libraIDs1, status1);

         List<Long> libraIDs2 = Arrays.asList(31L, 32L, 33L, 34L, 35L, 36L, 37L, 38L, 39L, 40L, 41L, 42L, 43L, 44L, 45L);

        InfoXSearchStatus status2 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult2 = new InfoXSearchResult(libraIDs2, status2);

        InfoXSearchStatus status3 = InfoXSearchStatus.FAILURE;
        InfoXSearchResult infoXSearchResult3 = new InfoXSearchResult(new ArrayList<>(), status3);

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
        List<Long> libraIDs1 = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L);

        NolAutoSearchResults autoSearch1 = new NolAutoSearchResults();
        autoSearch1.setLibraIds(libraIDs1);
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(NolStatuses.RESULTS_REJECTED.valueOf());


        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf())).thenReturn(nols);

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

        List<Nol> nols = new ArrayList<>();

        NolAutoSearchResults autoSearch1 = new NolAutoSearchResults();
        autoSearch1.setLibraIds(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L));

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(NolStatuses.RESULTS_REJECTED.valueOf());

        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf())).thenReturn(nols);

        List<Long> libraIDs1 = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 16L, 12L, 13L, 14L, 15L);

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

        List<Nol> nols = new ArrayList<>();

        List<Long> libraIDs1 = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 16L);

        NolAutoSearchResults autoSearch1 = new NolAutoSearchResults();
        autoSearch1.setLibraIds(libraIDs1);

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf())).thenReturn(nols);


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
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);

        List<Long> libraIDs1 = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L);

        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(libraIDs1, InfoXSearchStatus.SUCCESS);

        reconciliationService.updateNol(nol1, infoXSearchResult1);
        assertEquals(NolStatuses.RESULTS_FOUND.valueOf(),  nol1.getStatus());
    }

    @Test
    public void shouldReportMetrics() {
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(mock(NolAutoSearchResults.class));

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);

        List<Long> libraIds = Arrays.asList(123L);

        InfoXSearchResult infoXSearchResult = new InfoXSearchResult(libraIds, InfoXSearchStatus.SUCCESS);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.valueOf(), NolStatuses.LETTER_SENT.valueOf(), NolStatuses.RESULTS_REJECTED.valueOf())).thenReturn(Arrays.asList(nol1));
        when(infoXServiceClient.search(nol1)).thenReturn(infoXSearchResult);

        reconciliationService.reconcile();

        verify(metricHandler).recordReconciliationResult(ReconciliationResult.ONE_MATCH);
    }

    @Test
    public void shouldHandleSuccessWithNoMatches() {
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(mock(NolAutoSearchResults.class));

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);

        List<Long> nothing = new ArrayList<>();

        InfoXSearchResult infoXSearchResult = new InfoXSearchResult(nothing, InfoXSearchStatus.SUCCESS);

        when(infoXServiceClient.search(nol1)).thenReturn(infoXSearchResult);

        ReconciliationResult result = reconciliationService.reconcileNolRecord(nol1);
        assertEquals(ReconciliationResult.NO_MATCHES, result);
    }

    @Test
    public void shouldHandleSuccessWithManyMatches() {
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(mock(NolAutoSearchResults.class));

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);

        List<Long> libraIDs = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L);

        InfoXSearchResult infoXSearchResult = new InfoXSearchResult(libraIDs, InfoXSearchStatus.SUCCESS);

        when(infoXServiceClient.search(nol1)).thenReturn(infoXSearchResult);

        ReconciliationResult result = reconciliationService.reconcileNolRecord(nol1);
        assertEquals(ReconciliationResult.MANY_MATCHES, result);
    }

}
package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.common.NolStatus;
import com.laa.nolasa.laanolasa.common.ReconciliationResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResult;
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
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;
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
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setAutoSearchResults(LongStream.rangeClosed(1, 15).mapToObj(id -> new NolAutoSearchResult(id, nol1)).collect(Collectors.toList()));
        nol1.setStatus(NolStatus.RESULTS_REJECTED.getStatus());



        RepOrders repoOrder2 = new RepOrders();
        repoOrder2.setId(920L);
        Nol nol2 = new Nol();
        nol2.setRepOrders(repoOrder2);
        nol2.setAutoSearchResults(LongStream.rangeClosed(21, 35).mapToObj(id -> new NolAutoSearchResult(id, nol2)).collect(Collectors.toList()));

        Nol nol3 = new Nol();
        RepOrders repoOrder3 = new RepOrders();
        repoOrder3.setId(920L);
        nol3.setRepOrders(repoOrder3);

        nols.add(nol1);
        nols.add(nol2);
        nols.add(nol3);

        when(nolRepository.getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus())).thenReturn(nols);

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

        verify(nolRepository).getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus());
        verify(infoXServiceClient).search(nol1);
        verify(infoXServiceClient).search(nol2);
        verify(nolRepository).save(nol2);
        verify(nolRepository, never()).save(nol1);
    }


    @Test
    public void shouldNotProcessRejectedResultsWhenLibraIdsMatch() {

        List<Nol> nols = new ArrayList<Nol>();

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        Nol nol1 = new Nol();
        nol1.setAutoSearchResults(LongStream.rangeClosed(1, 15).mapToObj(id -> new NolAutoSearchResult(id, nol1)).collect(Collectors.toList()));
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(NolStatus.RESULTS_REJECTED.getStatus());


        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus())).thenReturn(nols);

        InfoXSearchStatus status1 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L), status1);


        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus());
        verify(infoXServiceClient).search(nol1);
        verify(nolRepository, never()).save(nol1);
    }

    @Test
    public void shouldProcessRejectedResultsWhenLibraIdsDoNotMatch() {

        List<Nol> nols = new ArrayList<>();

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setAutoSearchResults(LongStream.rangeClosed(1, 15).mapToObj(id -> new NolAutoSearchResult(id, nol1)).collect(Collectors.toList()));
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(NolStatus.RESULTS_REJECTED.getStatus());

        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus())).thenReturn(nols);

        InfoXSearchStatus status1 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 16L, 12L, 13L, 14L, 16L), status1);


        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus());
        verify(infoXServiceClient).search(nol1);
        verify(nolRepository).save(nol1);
    }

    @Test
    public void shouldNotSaveNolInDryRunMode() {

        List<Nol> nols = new ArrayList<>();

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setAutoSearchResults(LongStream.rangeClosed(1, 15).mapToObj(id -> new NolAutoSearchResult(id, nol1)).collect(Collectors.toList()));
        nol1.setRepOrders(repoOrder1);
        nols.add(nol1);

        when(nolRepository.getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus())).thenReturn(nols);

        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 16L), InfoXSearchStatus.SUCCESS);

        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        ReflectionTestUtils.setField(reconciliationService, "dryRunMode", true);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus());
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
        assertEquals(NolStatus.RESULTS_FOUND.getStatus(),  nol1.getStatus());
    }

    @Test
    public void shouldReportMetrics() {
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        List<NolAutoSearchResult> nolAutoAutoSearchResults = new ArrayList<>();
        NolAutoSearchResult mockAutoSearchResult = mock(NolAutoSearchResult.class);
        nol1.setAutoSearchResults(nolAutoAutoSearchResults);

        List<Long> libraIds = Arrays.asList(123L);

        InfoXSearchResult infoXSearchResult = new InfoXSearchResult(libraIds, InfoXSearchStatus.SUCCESS);

        when(nolRepository.getNolForAutoSearch(NolStatus.NOT_ON_LIBRA.getStatus(), NolStatus.LETTER_SENT.getStatus(), NolStatus.RESULTS_REJECTED.getStatus())).thenReturn(Arrays.asList(nol1));
        when(infoXServiceClient.search(nol1)).thenReturn(infoXSearchResult);

        reconciliationService.reconcile();

        verify(metricHandler).recordReconciliationResult(ReconciliationResult.ONE_MATCH);
    }

    @Test
    public void shouldHandleSuccessWithNoMatches() {
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        List<NolAutoSearchResult> nolAutoAutoSearchResults = new ArrayList<>();
        NolAutoSearchResult mockAutoSearchResult = mock(NolAutoSearchResult.class);
        nol1.setAutoSearchResults(nolAutoAutoSearchResults);

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

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        List<NolAutoSearchResult> nolAutoAutoSearchResults = new ArrayList<>();
        NolAutoSearchResult mockAutoSearchResult = mock(NolAutoSearchResult.class);
        nol1.setAutoSearchResults(nolAutoAutoSearchResults);

        List<Long> libraIDs = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L);

        InfoXSearchResult infoXSearchResult = new InfoXSearchResult(libraIDs, InfoXSearchStatus.SUCCESS);

        when(infoXServiceClient.search(nol1)).thenReturn(infoXSearchResult);

        ReconciliationResult result = reconciliationService.reconcileNolRecord(nol1);
        assertEquals(ReconciliationResult.MANY_MATCHES, result);
    }

}
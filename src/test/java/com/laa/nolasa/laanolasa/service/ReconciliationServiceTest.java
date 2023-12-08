package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.common.ReconciliationResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResult;
import com.laa.nolasa.laanolasa.entity.RepOrders;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import com.laa.nolasa.laanolasa.util.MetricHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.laa.nolasa.laanolasa.common.NolStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReconciliationServiceTest {

    @Mock
    private InfoXServiceClient infoXServiceClient;

    @Mock
    private NolRepository nolRepository;

    @Mock
    private MetricHandler metricHandler;

    private ReconciliationService reconciliationService;

    @BeforeEach
    public void setUp()  {
        reconciliationService = new ReconciliationService(infoXServiceClient, nolRepository, metricHandler);
    }

    @Test
    public void shouldReconcileLibraIDs() {

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setAutoSearchResults(createSearchResultsWithIdRange(1, 15, nol1));
        nol1.setStatus(RESULTS_REJECTED.getStatus());


        RepOrders repoOrder2 = new RepOrders();
        repoOrder2.setId(920L);
        Nol nol2 = new Nol();
        nol2.setRepOrders(repoOrder2);
        nol2.setAutoSearchResults(createSearchResultsWithIdRange(21, 35, nol2));

        Nol nol3 = new Nol();
        RepOrders repoOrder3 = new RepOrders();
        repoOrder3.setId(920L);
        nol3.setRepOrders(repoOrder3);

        List<Nol> nols = List.of(nol1, nol2, nol3);

        when(nolRepository.getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED)).thenReturn(nols);

        InfoXSearchResult infoXSearchResult1 = createInfoXSearchResultWithIdRange(1L, 15L, InfoXSearchStatus.SUCCESS);

        InfoXSearchResult infoXSearchResult2 = createInfoXSearchResultWithIdRange(31L, 45L, InfoXSearchStatus.SUCCESS);

        InfoXSearchStatus status3 = InfoXSearchStatus.FAILURE;
        InfoXSearchResult infoXSearchResult3 = new InfoXSearchResult(new ArrayList<>(), status3);

        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1).thenReturn(infoXSearchResult2).thenReturn(infoXSearchResult3);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED);
        verify(infoXServiceClient).search(nol1);
        verify(infoXServiceClient).search(nol2);
        verify(nolRepository).save(nol2);
        verify(nolRepository, never()).save(nol1);
    }

    @Test
    public void shouldNotProcessRejectedResultsWhenLibraIdsMatch() {

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        Nol nol1 = new Nol();
        nol1.setAutoSearchResults(createSearchResultsWithIdRange(1, 15, nol1));
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(RESULTS_REJECTED.getStatus());

        List<Nol> nols = List.of(nol1);

        when(nolRepository.getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED)).thenReturn(nols);

        InfoXSearchResult infoXSearchResult1 = createInfoXSearchResultWithIdRange(1L,  15L, InfoXSearchStatus.SUCCESS);


        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED);
        verify(infoXServiceClient).search(nol1);
        verify(nolRepository, never()).save(nol1);
    }

    @Test
    public void shouldProcessRejectedResultsWhenLibraIdsDoNotMatch() {

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setAutoSearchResults(createSearchResultsWithIdRange(1, 15, nol1));
        nol1.setRepOrders(repoOrder1);
        nol1.setStatus(RESULTS_REJECTED.getStatus());

        List<Nol> nols = List.of(nol1);

        when(nolRepository.getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED)).thenReturn(nols);

        InfoXSearchResult infoXSearchResult1 = createInfoXSearchResultWithIdRange(1L,  16L, InfoXSearchStatus.SUCCESS);


        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED);
        verify(infoXServiceClient).search(nol1);
        verify(nolRepository).save(nol1);
    }

    @Test
    public void shouldNotSaveNolInDryRunMode() {

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setAutoSearchResults(createSearchResultsWithIdRange(1, 15, nol1));
        nol1.setRepOrders(repoOrder1);
        List<Nol> nols = List.of(nol1);

        when(nolRepository.getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED)).thenReturn(nols);

        InfoXSearchResult infoXSearchResult1 = createInfoXSearchResultWithIdRange(1L,  16L, InfoXSearchStatus.SUCCESS);

        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1);

        ReflectionTestUtils.setField(reconciliationService, "dryRunMode", true);

        reconciliationService.reconcile();

        verify(nolRepository).getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED);
        verify(infoXServiceClient).search(nol1);
        verify(nolRepository, never()).save(nol1);
    }

    @Test
    public void shouldUpdateNolSucceedWhenNoPreviousAutoSearchResult() {
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);

        InfoXSearchResult infoXSearchResult1 = createInfoXSearchResultWithIdRange(1L,  15L, InfoXSearchStatus.SUCCESS);

        reconciliationService.updateNol(nol1, infoXSearchResult1);
        assertEquals(RESULTS_FOUND.getStatus(), nol1.getStatus());
    }

    @Test
    public void shouldReportMetrics() {
        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        List<NolAutoSearchResult> nolAutoAutoSearchResults = new ArrayList<>();
        nol1.setAutoSearchResults(nolAutoAutoSearchResults);

        InfoXSearchResult infoXSearchResult = new InfoXSearchResult(List.of(123L), InfoXSearchStatus.SUCCESS);

        when(nolRepository.getNolForAutoSearch(NOT_ON_LIBRA, LETTER_SENT, RESULTS_REJECTED)).thenReturn(List.of(nol1));
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
        nol1.setAutoSearchResults(nolAutoAutoSearchResults);

        InfoXSearchResult infoXSearchResult = createInfoXSearchResultWithIdRange(1, 15, InfoXSearchStatus.SUCCESS);

        when(infoXServiceClient.search(nol1)).thenReturn(infoXSearchResult);

        ReconciliationResult result = reconciliationService.reconcileNolRecord(nol1);
        assertEquals(ReconciliationResult.MANY_MATCHES, result);
    }

    private List<NolAutoSearchResult> createSearchResultsWithIdRange(long startIdInclusive,
                                                                     long endIdInclusive,
                                                                     Nol notOnLibra) {
        return LongStream.rangeClosed(startIdInclusive, endIdInclusive)
                .mapToObj(id -> new NolAutoSearchResult(id, notOnLibra))
                .collect(Collectors.toList());
    }

    private InfoXSearchResult createInfoXSearchResultWithIdRange(long startIdInclusive,
                                                                 long endIdInclusive,
                                                                 InfoXSearchStatus status) {
        List<Long> libraIDs = LongStream.rangeClosed(startIdInclusive, endIdInclusive)
                .boxed()
                .collect(Collectors.toList());

        return new InfoXSearchResult(libraIDs, status);
    }
}
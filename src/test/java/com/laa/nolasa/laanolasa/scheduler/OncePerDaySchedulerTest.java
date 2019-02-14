package com.laa.nolasa.laanolasa.scheduler;

import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResult;
import com.laa.nolasa.laanolasa.entity.RepOrders;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import com.laa.nolasa.laanolasa.service.InfoXServiceClient;
import com.laa.nolasa.laanolasa.service.ReconciliationService;
import com.laa.nolasa.laanolasa.util.MetricHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OncePerDaySchedulerTest {

    @Mock
    private InfoXServiceClient infoXServiceClient;

    @Mock
    private NolRepository nolRepository;

    @Mock
    private MetricHandler metricHandler;

    private OncePerDayScheduler oncePerDayScheduler;

    @Before
    public void setUp()  {

        ReconciliationService reconciliationService = new ReconciliationService(nolRepository, infoXServiceClient, metricHandler);
        oncePerDayScheduler = new OncePerDayScheduler(reconciliationService);
    }

    @Test
    public void reconcile() {
        List<Nol> nols = new ArrayList<>();

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);
        nol1.setAutoSearchResults(LongStream.range(1, 15).mapToObj(id -> new NolAutoSearchResult(id, nol1)).collect(Collectors.toList()));

        RepOrders repoOrder2 = new RepOrders();
        repoOrder2.setId(920L);

        Nol nol2 = new Nol();
        nol2.setRepOrders(repoOrder2);
        nol2.setAutoSearchResults(LongStream.range(21, 35).mapToObj(id -> new NolAutoSearchResult(id, nol1)).collect(Collectors.toList()));

        Nol nol3 = new Nol();
        RepOrders repoOrder3 = new RepOrders();
        repoOrder3.setId(920L);
        nol3.setRepOrders(repoOrder3);

        nols.add(nol1);
        nols.add(nol2);
        nols.add(nol3);

        when(nolRepository.getNolForAutoSearch(NolStatuses.NOT_ON_LIBRA.getStatus(), NolStatuses.LETTER_SENT.getStatus(), NolStatuses.RESULTS_REJECTED.getStatus())).thenReturn(nols);

        InfoXSearchStatus status1 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L), status1);

        InfoXSearchStatus status2 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult2 = new InfoXSearchResult(Arrays.asList(21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L, 30L, 31L, 32L, 33L, 34L, 35L), status2);

        InfoXSearchStatus status3 = InfoXSearchStatus.FAILURE;
        InfoXSearchResult infoXSearchResult3 = new InfoXSearchResult(new ArrayList<>(), status3);

        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1).thenReturn(infoXSearchResult2).thenReturn(infoXSearchResult3);


        oncePerDayScheduler.reconcile();
    }
}
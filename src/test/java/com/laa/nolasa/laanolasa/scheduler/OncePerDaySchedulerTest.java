package com.laa.nolasa.laanolasa.scheduler;

import com.laa.nolasa.laanolasa.common.NolStatuses;
import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;
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
import java.util.List;

import static org.junit.Assert.*;
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

    private ReconciliationService reconciliationService;

    private OncePerDayScheduler oncePerDayScheduler;

    @Before
    public void setUp() throws Exception {
        reconciliationService = new ReconciliationService(nolRepository, infoXServiceClient, metricHandler);
        oncePerDayScheduler = new OncePerDayScheduler(reconciliationService);
    }

    @Test
    public void reconcile() {
        List<Nol> nols = new ArrayList<Nol>();

        NolAutoSearchResults autoSearch1 = new NolAutoSearchResults();
        autoSearch1.setLibrId1(1L);
        autoSearch1.setLibrId2(2L);
        autoSearch1.setLibrId3(3L);
        autoSearch1.setLibrId4(4L);
        autoSearch1.setLibrId5(5L);
        autoSearch1.setLibrId6(6L);
        autoSearch1.setLibrId7(7L);
        autoSearch1.setLibrId8(8L);
        autoSearch1.setLibrId9(9L);
        autoSearch1.setLibrId10(10L);
        autoSearch1.setLibrId11(11L);
        autoSearch1.setLibrId12(12L);
        autoSearch1.setLibrId13(13L);
        autoSearch1.setLibrId14(14L);
        autoSearch1.setLibrId15(15L);

        RepOrders repoOrder1 = new RepOrders();
        repoOrder1.setId(901L);
        repoOrder1.setNolAutoSearchResults(autoSearch1);

        Nol nol1 = new Nol();
        nol1.setRepOrders(repoOrder1);

        NolAutoSearchResults autoSearch2 = new NolAutoSearchResults();
        autoSearch2.setLibrId1(21L);
        autoSearch2.setLibrId2(22L);
        autoSearch2.setLibrId3(23L);
        autoSearch2.setLibrId4(24L);
        autoSearch2.setLibrId5(25L);
        autoSearch2.setLibrId6(26L);
        autoSearch2.setLibrId7(27L);
        autoSearch2.setLibrId8(28L);
        autoSearch2.setLibrId9(29L);
        autoSearch2.setLibrId10(210L);
        autoSearch2.setLibrId11(211L);
        autoSearch2.setLibrId12(212L);
        autoSearch2.setLibrId13(213L);
        autoSearch2.setLibrId14(214L);
        autoSearch2.setLibrId15(215L);

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

        Long[] libraIDs1 = new Long[15];
        libraIDs1[0] = 1L;
        libraIDs1[1] = 2L;
        libraIDs1[2] = 3L;
        libraIDs1[3] = 4L;
        libraIDs1[4] = 5L;
        libraIDs1[5] = 6L;
        libraIDs1[6] = 7L;
        libraIDs1[7] = 8L;
        libraIDs1[8] = 9L;
        libraIDs1[9] = 10L;
        libraIDs1[10] = 11L;
        libraIDs1[11] = 12L;
        libraIDs1[12] = 13L;
        libraIDs1[13] = 14L;
        libraIDs1[14] = 15L;

        InfoXSearchStatus status1 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult1 = new InfoXSearchResult(libraIDs1, status1);

        Long[] libraIDs2 = new Long[15];
        libraIDs2[0] = 31L;
        libraIDs2[1] = 32L;
        libraIDs2[2] = 33L;
        libraIDs2[3] = 34L;
        libraIDs2[4] = 35L;
        libraIDs2[5] = 36L;
        libraIDs2[6] = 37L;
        libraIDs2[7] = 38L;
        libraIDs2[8] = 39L;
        libraIDs2[9] = 310L;
        libraIDs2[10] = 311L;
        libraIDs2[11] = 312L;
        libraIDs2[12] = 313L;
        libraIDs2[13] = 314L;
        libraIDs2[14] = 315L;

        InfoXSearchStatus status2 = InfoXSearchStatus.SUCCESS;
        InfoXSearchResult infoXSearchResult2 = new InfoXSearchResult(libraIDs2, status2);

        InfoXSearchStatus status3 = InfoXSearchStatus.FAILURE;
        InfoXSearchResult infoXSearchResult3 = new InfoXSearchResult(new Long[15], status3);

        when(infoXServiceClient.search(any(Nol.class))).thenReturn(infoXSearchResult1).thenReturn(infoXSearchResult2).thenReturn(infoXSearchResult3);


        oncePerDayScheduler.reconcile();
    }
}
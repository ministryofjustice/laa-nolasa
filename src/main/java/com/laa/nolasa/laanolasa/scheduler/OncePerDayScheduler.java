package com.laa.nolasa.laanolasa.scheduler;

import com.laa.nolasa.laanolasa.service.ReconciliationService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OncePerDayScheduler {

    private final ReconciliationService reconciliationService;

    public OncePerDayScheduler(ReconciliationService reconciliationService) {
        this.reconciliationService = reconciliationService;
    }

    @Timed(value = "reconciliation.tasks", longTask = true)
    @Scheduled(cron = "${app.cron.string}")
    public void reconcile() {
        reconciliationService.reconcile();
    }

}

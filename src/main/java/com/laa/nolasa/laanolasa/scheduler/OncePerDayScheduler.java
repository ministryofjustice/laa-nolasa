package com.laa.nolasa.laanolasa.scheduler;

import com.laa.nolasa.laanolasa.service.ReconciliationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OncePerDayScheduler {
    private static final int TWENTY_SECONDS = 20000;
    private static final String EVERYDAY_8PM = "0 0 20 ? * * *";
    private final ReconciliationService reconciliationService;

    public OncePerDayScheduler(ReconciliationService reconciliationService) {
        this.reconciliationService = reconciliationService;
    }

    @Scheduled(fixedDelay = TWENTY_SECONDS)
    //@Scheduled(cron = EVERYDAY_8PM)
    public void reconcile() {
        try {
            log.debug("calling service reconciliationService");
            reconciliationService.reconcile();
        } catch (Exception ex) {
            log.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

}

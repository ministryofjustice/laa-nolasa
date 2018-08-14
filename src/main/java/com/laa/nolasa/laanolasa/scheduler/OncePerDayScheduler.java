package com.laa.nolasa.laanolasa.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class OncePerDayScheduler {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 2000)
    //@Scheduled(cron = "0 0 20 ? * * *")
    public void reconcile() {
        log.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            //TimeUnit.SECONDS.sleep(5);
        } catch (Exception ex) {
            log.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

}

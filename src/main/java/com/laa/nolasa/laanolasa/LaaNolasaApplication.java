package com.laa.nolasa.laanolasa;

import com.amazonaws.xray.AWSXRay;
import com.laa.nolasa.laanolasa.service.ReconciliationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
public class LaaNolasaApplication implements CommandLineRunner {


    @Autowired
    private  ReconciliationService reconciliationService;

    public static void main(String[] args) {
        System.out.println("Calling the application");
        new SpringApplicationBuilder(LaaNolasaApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("About to call reconcile");
        AWSXRay.beginSegment("Reconcile");
        reconciliationService.reconcile();
        AWSXRay.endSegment();
    }
}

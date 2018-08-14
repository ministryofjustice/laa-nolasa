package com.laa.nolasa.laanolasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LaaNolasaApplication {

    public static void main(String[] args) {
        System.out.println("Calling the application");
        SpringApplication.run(LaaNolasaApplication.class, args);
    }
}

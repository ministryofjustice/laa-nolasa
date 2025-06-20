package com.laa.nolasa.laanolasa.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PostConstructLogger {

    @Autowired
    private Environment environment;

    private static final List<String> ENVIRONMENT_PROPERTY_KEYS = List.of(
            "APP_CRON_STRING",
            "APP_DRY_RUN_MODE",
            "LIBRA_ENDPOINTURI",
            "DATASOURCE_USERNAME",
            "DATASOURCE_URL",
            "AWS_ACCESS_KEY_ID",
            "CLOUDWATCH_EXPORT_ENABLED",
            "CLOUDWATCH_STEP",
            "spring.datasource.url",
            "spring.datasource.username",
            "spring.datasource.driver-class-name",
            "spring.jpa.show-sql",
            "app.cron.string",
            "debug",
            "client.default-uri");

    @PostConstruct
    public void init() {
        log.info("====== Environment and configuration ======");
        logEnvironmentProperties();
        logSlf4jLoggingImplementation();
        log.info("===========================================");
    }

    private static void logSlf4jLoggingImplementation() {
        log.info("SLF4J is using the logging implementation: {}", log.getClass().getCanonicalName());
    }

    private void logEnvironmentProperties() {
        ENVIRONMENT_PROPERTY_KEYS
                .forEach(propertyKey -> log.info("ENVIRONMENT_PROPERTY: {} VALUE: {}", propertyKey, environment.getProperty(propertyKey)));
    }
}
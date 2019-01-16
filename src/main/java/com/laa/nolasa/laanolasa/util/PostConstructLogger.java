package com.laa.nolasa.laanolasa.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Component
@Slf4j
public class PostConstructLogger {

    @Autowired
    private Environment env;

    private static final List<String> PROPS = Arrays.asList(
            "APP_CRON_STRING",
            "LIBRA_ENDPOINTURI",
            "DATASOURCE_USERNAME",
            "DATASOURCE_URL",
            "spring.datasource.url",
            "spring.datasource.username",
            "spring.datasource.driver-class-name",
            "spring.jpa.show-sql",
            "app.cron.string",
            "debug",
            "client.default-uri",
            "logging.level.org.springframework.web",
            "logging.level.org.springframework.ws.client.MessageTracing.sent",
            "logging.level.org.springframework.ws.client.MessageTracing.received",
            "logging.level.org.springframework.ws.server.MessageTracing.sent",
            "logging.level.org.springframework.ws.server.MessageTracing.received");


    @PostConstruct
    public void init() {

        log.info("====== Environment and configuration ======");

        PROPS.stream()
                .forEach(prop -> log.info("{}: {}", prop, env.getProperty(prop)));

        log.info("===========================================");
    }
}
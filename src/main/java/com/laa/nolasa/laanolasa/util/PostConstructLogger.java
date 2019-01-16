package com.laa.nolasa.laanolasa.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.StreamSupport;


@Component
@Slf4j
public class PostConstructLogger {

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {

        log.info("====== Environment and configuration ======");
        log.info("Active profiles: {}", Arrays.toString(env.getActiveProfiles()));
        final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
        StreamSupport.stream(sources.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .filter(prop -> !(prop.contains("credentials") || prop.contains("password")))
                .forEach(prop -> log.info("{}: {}", prop, env.getProperty(prop)));
        log.info("===========================================");
    }
}
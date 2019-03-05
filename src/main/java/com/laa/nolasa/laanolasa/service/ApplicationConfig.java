package com.laa.nolasa.laanolasa.service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import uk.gov.justice._2013._11.magistrates.LIBRAServicePortType;

@Configuration
public class ApplicationConfig {

  @Value("${client.default-uri}")
  private String defaultUri;

  @Bean
  @Primary
  public AWSCredentialsProvider awsCredentialsProvider() {
    return new DefaultAWSCredentialsProviderChain();
  }

  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
  }

  @Bean(name = "infoxProxy")
  public LIBRAServicePortType helloWorldProxy() {
    JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
    jaxWsProxyFactoryBean.setServiceClass(LIBRAServicePortType.class);
    jaxWsProxyFactoryBean.setAddress(defaultUri);

    return (LIBRAServicePortType) jaxWsProxyFactoryBean.create();
  }
}
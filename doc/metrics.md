# Metrics

Metrics are collected using [Micrometer](http://micrometer.io/).

See [production ready metrics](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html)
for how this integrates with Spring boot.

In production/test, metrics are exported to AWS Cloudwatch.

To inspect the metrics in development, enable the web endpoints in `application.yml`:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

You can then view metrics at http://localhost:8081/actuator/metrics
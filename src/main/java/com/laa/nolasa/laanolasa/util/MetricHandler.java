package com.laa.nolasa.laanolasa.util;

import com.laa.nolasa.laanolasa.common.ReconciliationResult;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Component;

@Component
public class MetricHandler {
    private Counter singleMatchQueries;
    private Counter multiMatchQueries;
    private Counter noMatchQueries;
    private Counter errorQueries;
    private Counter alreadyRejectedQueries;

    public MetricHandler() {
        // Counts of queries with each outcome
        this.singleMatchQueries = Metrics.globalRegistry.counter("queries.singleMatch");
        this.multiMatchQueries = Metrics.globalRegistry.counter("queries.multiMatch");
        this.noMatchQueries = Metrics.globalRegistry.counter("queries.noMatch");
        this.errorQueries = Metrics.globalRegistry.counter("queries.error");
        this.alreadyRejectedQueries = Metrics.globalRegistry.counter("queries.alreadyRejected");
    }

    public void recordReconciliationResult(ReconciliationResult result) {
        switch (result) {
            case ERROR:
                this.errorQueries.increment();
                break;
            case ONE_MATCH:
                this.singleMatchQueries.increment();
                break;
            case MANY_MATCHES:
                this.multiMatchQueries.increment();
                break;
            case NO_MATCHES:
                this.noMatchQueries.increment();
                break;
            case MATCHES_ALREADY_REJECTED:
                this.alreadyRejectedQueries.increment();
                break;
        }
    }
}

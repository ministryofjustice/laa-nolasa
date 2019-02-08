package com.laa.nolasa.laanolasa.util;

import com.laa.nolasa.laanolasa.common.ReconciliationResult;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MetricHandler {
    private Counter singleMatchQueries;
    private Counter multiMatchQueries;
    private Counter noMatchQueries;
    private Counter errorQueries;
    private Counter alreadyRejectedQueries;
    private DistributionSummary numberOfResults;

    public MetricHandler(MeterRegistry registry) {
        this.singleMatchQueries = Counter.builder("result.single_match")
            .description("Number of queries returning a single match")
            .register(registry);

        this.multiMatchQueries = Counter.builder("result.multi_match")
                .description("Number of queries returning multiple matches")
                .register(registry);

        this.noMatchQueries = Counter.builder("result.no_match")
                .description("Number of queries returning no matches")
                .register(registry);

        this.errorQueries = Counter.builder("result.error")
                .description("Number of exceptions thrown while running a query or updating the database")
                .register(registry);

        this.alreadyRejectedQueries = Counter.builder("result.already_rejected")
                .description("Number of queries that returned matches that have already been rejected by a case worker")
                .register(registry);

        this.numberOfResults = DistributionSummary.builder("number_of_results")
                .description("Distribution of sizes of Libra result sets")
                .publishPercentileHistogram()
                .register(registry);
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

    public void recordNumberOfResults(int numberOfResults) {
        this.numberOfResults.record(numberOfResults);
    }
}

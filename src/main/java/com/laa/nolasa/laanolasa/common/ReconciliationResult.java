package com.laa.nolasa.laanolasa.common;

public enum ReconciliationResult {
    MATCHES_ALREADY_REJECTED,
    NO_MATCHES,
    ONE_MATCH,
    MANY_MATCHES,
    OVER_15_MATCHES,
    ERROR;

    public static ReconciliationResult fromCount(Long count) {
        if (count < 0) return ERROR;
        if (count == 0) return NO_MATCHES;
        if (count == 1) return ONE_MATCH;
        if (count > 15) return OVER_15_MATCHES;
        return MANY_MATCHES;
    }
}

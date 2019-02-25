package com.laa.nolasa.laanolasa.common;

import java.util.Arrays;

public enum InfoxStatus {
    LIBRA_SUCCESS_CODE("1"),
    LIBRA_GREATER_THAN_15_CODE("100100"),
    LIBRA_NO_MATCH_FOUND("100000"),
    LIBRA_FAILED_EXCEPTION("999999"),
    LIBRA_INVALID_CODE("invalid");

    private final String code;

    InfoxStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public static InfoxStatus fromString(String value) {

        return Arrays.stream(InfoxStatus.values())
                     .filter(s -> s.code.equalsIgnoreCase(value))
                     .findFirst()
                     .orElse(InfoxStatus.LIBRA_INVALID_CODE);
    }
}

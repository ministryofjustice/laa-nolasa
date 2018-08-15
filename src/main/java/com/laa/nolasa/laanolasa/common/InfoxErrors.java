package com.laa.nolasa.laanolasa.common;

public enum InfoxErrors {
    LIBRA_SUCCESS_CODE("1"),
    LIBRA_GREATER_THAN_15_CODE("100100"),
    LIBRA_FAILED_EXCEPTION("999999");

    private String value;

    InfoxErrors(String value) {
        this.value = value;
    }

    public String valueOf() {
        return this.value;
    }
}

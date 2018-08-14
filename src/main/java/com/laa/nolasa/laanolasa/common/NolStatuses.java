package com.laa.nolasa.laanolasa.common;

public enum NolStatuses {

    NOT_ON_LIBRA("NOT ON LIBRA"),
    LETTER_SENT("LETTER SENT"),
    RESULTS_REJECTED("RESULTS REJECTED"),
    RESULTS_FOUND("RESULTS FOUND");

    private String value;

    NolStatuses(String value) {
        this.value = value;
    }

    public String valueOf() {
        return this.value;
    }
}

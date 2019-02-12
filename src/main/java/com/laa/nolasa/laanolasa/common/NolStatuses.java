package com.laa.nolasa.laanolasa.common;

public enum NolStatuses {

    NOT_ON_LIBRA("NOT ON LIBRA"),
    LETTER_SENT("LETTER SENT"),
    RESULTS_REJECTED("RESULTS REJECTED"),
    RESULTS_FOUND("RESULTS FOUND");

    private String status;

    NolStatuses(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}

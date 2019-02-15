package com.laa.nolasa.laanolasa.common;

public enum NolStatus {

    NOT_ON_LIBRA("NOT ON LIBRA"),
    LETTER_SENT("LETTER SENT"),
    RESULTS_REJECTED("RESULTS REJECTED"),
    RESULTS_FOUND("RESULTS FOUND");

    private String status;

    NolStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}

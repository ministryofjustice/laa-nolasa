package com.laa.nolasa.laanolasa.exception;

public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

    public AuthException(Throwable rootCause) {
        super(rootCause);
    }

    public AuthException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}

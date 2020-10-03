package com.db2020.pj.exception.custom;

public class CAuthenticationEntryPointException extends RuntimeException {
    public CAuthenticationEntryPointException(String msg, Throwable t) {
        super(msg, t);
    }

    public CAuthenticationEntryPointException(String msg) {
        super(msg);
    }

    public CAuthenticationEntryPointException() {
        super();
    }
}
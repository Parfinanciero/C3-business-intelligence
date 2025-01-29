package com.BI.Exceptions.Custom;

public class NoCashFoundException extends RuntimeException {
    public NoCashFoundException(String message) {
        super(message);
    }
}

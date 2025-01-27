package com.BI.Exceptions;

public class NoCashFoundException extends RuntimeException {
    public NoCashFoundException(String message) {
        super(message);
    }
}

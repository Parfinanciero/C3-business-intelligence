package com.BI.Exceptions.Custom;

public class NoIncomeException extends RuntimeException {
    public NoIncomeException(String message) {
        super(message);
    }
}

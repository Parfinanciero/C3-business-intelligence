package com.BI.Exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
//@Builder
public class ErrorResponse {
    public ErrorResponse(String message, int status, String details) {
        this.message = message;
        this.status = status;
        this.details = details;
    }

    private String message;
    private int status;
    private String details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

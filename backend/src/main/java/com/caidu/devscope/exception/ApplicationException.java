package com.caidu.devscope.exception;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final int statusCode;

    public ApplicationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApplicationException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

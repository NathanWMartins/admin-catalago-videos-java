package com.admin.catalago.domain.exceptions;

public class NoStracktraceException extends RuntimeException{
    public NoStracktraceException(String message) {
        this(message, null);
    }

    public NoStracktraceException(String message, Throwable cause) {
        super(message, cause, true, false);
    }
}

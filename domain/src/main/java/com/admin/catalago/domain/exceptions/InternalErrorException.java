package com.admin.catalago.domain.exceptions;

import com.admin.catalago.domain.validation.Error;

import java.util.List;

public class InternalErrorException extends NoStracktraceException {

    protected InternalErrorException(final String aMessage, final Throwable t) {
        super(aMessage, t);
    }

    public static InternalErrorException with(final String message, final Throwable t) {
        return new InternalErrorException(message, t);
    }
}
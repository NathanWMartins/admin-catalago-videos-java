package com.admin.catalago.domain.validation.handler;

import com.admin.catalago.domain.exceptions.DomainException;
import com.admin.catalago.domain.exceptions.NotificationExceptions;
import com.admin.catalago.domain.validation.Error;
import com.admin.catalago.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandlers implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {
        throw NotificationExceptions.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw NotificationExceptions.with(anHandler.getErrors());
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();
        } catch (final Exception ex) {
            throw NotificationExceptions.with(new Error(ex.getMessage()));
        }
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
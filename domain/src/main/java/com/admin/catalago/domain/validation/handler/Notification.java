package com.admin.catalago.domain.validation.handler;

import com.admin.catalago.domain.exceptions.NotificationExceptions;
import com.admin.catalago.domain.validation.Error;
import com.admin.catalago.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

    private final List<Error> erros;

    public Notification(final List<Error> erros) {
        this.erros = erros;
    }

    public static Notification create(){
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Throwable t){
        return create(new Error(t.getMessage()));
    }

    public static Notification create(final Error anError){
        return new Notification(new ArrayList<>()).append(anError);
    }

    @Override
    public Notification append(final Error anError) {
        this.erros.add(anError);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler anHandler) {
        this.erros.addAll(anHandler.getErrors());
        return this;
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();
        }catch (final Exception ex){
            throw NotificationExceptions.with(new Error(ex.getMessage()));
        }
    }

    @Override
    public List<Error> getErrors() {
        return this.erros;
    }
}

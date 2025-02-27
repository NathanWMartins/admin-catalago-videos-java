package com.admin.catalago.domain.exceptions;

import com.admin.catalago.domain.validation.handler.Notification;


public class NotificationExceptions extends DomainException {

    public NotificationExceptions(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}

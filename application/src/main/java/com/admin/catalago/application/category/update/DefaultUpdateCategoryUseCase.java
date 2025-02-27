package com.admin.catalago.application.category.update;

import com.admin.catalago.domain.category.Category;
import com.admin.catalago.domain.category.CategoryGateway;
import com.admin.catalago.domain.category.CategoryID;
import com.admin.catalago.domain.exceptions.NotificationExceptions;
import com.admin.catalago.domain.validation.Error;
import com.admin.catalago.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase{
    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }


    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
        final var anId = CategoryID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = this.categoryGateway.findById(anId)
                .orElseThrow(() -> NotificationExceptions.with(
                        new Error("Category with ID %s was not found".formatted(anId.getValue()))));

        final var notification = Notification.create();
        aCategory
                .update(aName, aDescription, isActive)
                .validate(notification);

        return notification.hasError() ? API.Left(notification) : update(aCategory);
    }
    private Either<Notification, UpdateCategoryOutput> update(final Category aCategory){
        return API.Try(() -> this.categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }
}

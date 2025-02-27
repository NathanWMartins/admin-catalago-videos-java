package com.admin.catalago.application.category.create;

import com.admin.catalago.domain.category.Category;
import com.admin.catalago.domain.category.CategoryGateway;
import com.admin.catalago.domain.validation.handler.Notification;
import com.admin.catalago.domain.validation.handler.ThrowsValidationHandlers;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var name = aCommand.name();
        final var description = aCommand.description();
        final var isActive = aCommand.isActive();


        final var notification = Notification.create();
        final var category = Category.newCategory(name, description, isActive);
        category.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(category);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category category) {
        return Try(() -> this.categoryGateway.create(category))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}

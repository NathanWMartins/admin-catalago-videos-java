package com.admin.catalago.application.category.create;

import com.admin.catalago.application.UseCase;
import com.admin.catalago.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}

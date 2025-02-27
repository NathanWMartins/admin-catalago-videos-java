package com.admin.catalago.application.category.update;

import com.admin.catalago.application.UseCase;
import com.admin.catalago.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}

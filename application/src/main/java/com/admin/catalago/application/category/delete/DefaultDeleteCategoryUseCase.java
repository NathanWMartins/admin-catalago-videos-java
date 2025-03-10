package com.admin.catalago.application.category.delete;

import com.admin.catalago.domain.category.CategoryGateway;
import com.admin.catalago.domain.category.CategoryID;

import java.util.Objects;

public class DefaultDeleteCategoryUseCase
        extends DeleteCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultDeleteCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }


    @Override
    public void execute(final String anIn) {
        this.categoryGateway.deleteById(CategoryID.from(anIn));
    }
}

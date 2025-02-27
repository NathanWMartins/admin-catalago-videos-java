package com.admin.catalago.infrasctructure.category.presenters;

import com.admin.catalago.application.category.retrieve.get.CategoryOutput;
import com.admin.catalago.application.category.retrieve.list.CategoryListOutput;
import com.admin.catalago.infrasctructure.category.models.CategoryResponse;
import com.admin.catalago.infrasctructure.category.models.CategoryListResponse;

public interface CategoryAPIPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput output) {
        return new CategoryListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }

}

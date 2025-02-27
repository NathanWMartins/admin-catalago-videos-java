package com.admin.catalago.application.category.update;

import com.admin.catalago.domain.category.Category;
import com.admin.catalago.domain.category.CategoryID;

public record UpdateCategoryOutput(
        String id
) {

    public static UpdateCategoryOutput from(final String anId){
        return new UpdateCategoryOutput(anId);
    }

    public static UpdateCategoryOutput from(final Category aCategory){
        return new UpdateCategoryOutput(aCategory.getId().getValue());
    }
}

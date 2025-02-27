package com.admin.catalago.application.category.create;

import com.admin.catalago.domain.category.Category;
import com.admin.catalago.domain.category.CategoryID;

public record CreateCategoryOutput(
        String id
) {
    public static CreateCategoryOutput from(final String anID){
        return new CreateCategoryOutput(anID);
    }

    public static CreateCategoryOutput from(final Category aCategory){
        return new CreateCategoryOutput(aCategory.getId().getValue());
    }
}

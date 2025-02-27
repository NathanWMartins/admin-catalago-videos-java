package com.admin.catalago.application.category.retrieve.list;

import com.admin.catalago.application.UseCase;
import com.admin.catalago.domain.pagination.SearchQuery;
import com.admin.catalago.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}

package com.admin.catalago.application.genre.retrieve.list;

import com.admin.catalago.application.UseCase;
import com.admin.catalago.domain.pagination.Pagination;
import com.admin.catalago.domain.pagination.SearchQuery;

public abstract class ListGenreUseCase
        extends UseCase<SearchQuery, Pagination<GenreListOutput>> {
}
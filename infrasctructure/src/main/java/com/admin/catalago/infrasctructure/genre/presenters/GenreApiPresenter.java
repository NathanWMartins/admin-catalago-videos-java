package com.admin.catalago.infrasctructure.genre.presenters;

import com.admin.catalago.application.genre.retrieve.get.GenreOutput;
import com.admin.catalago.application.genre.retrieve.list.GenreListOutput;
import com.admin.catalago.infrasctructure.genre.models.GenreListResponse;
import com.admin.catalago.infrasctructure.genre.models.GenreResponse;

public interface GenreApiPresenter {

    static GenreResponse present(final GenreOutput output) {
        return new GenreResponse(
                output.id(),
                output.name(),
                output.categories(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static GenreListResponse present(final GenreListOutput output) {
        return new GenreListResponse(
                output.id(),
                output.name(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
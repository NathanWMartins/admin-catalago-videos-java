package com.admin.catalago.application.genre.create;

import com.admin.catalago.domain.category.Category;
import com.admin.catalago.domain.genre.Genre;

public record CreateGenreOutput(
        String id
) {
    public static CreateGenreOutput from(final String anID){
        return new CreateGenreOutput(anID);
    }

    public static CreateGenreOutput from(final Genre aGenre){
        return new CreateGenreOutput(aGenre.getId().getValue());
    }
}

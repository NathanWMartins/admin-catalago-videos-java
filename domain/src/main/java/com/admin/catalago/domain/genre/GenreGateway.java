package com.admin.catalago.domain.genre;

import com.admin.catalago.domain.pagination.Pagination;
import com.admin.catalago.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface GenreGateway {

    Genre create(Genre aGenre);

    void deleteById(GenreID anId);

    Optional<Genre> findById(GenreID anId);

    Genre update(Genre aGenre);

    Pagination<Genre> findAll(SearchQuery query);

    List<GenreID> existsByIds(Iterable<GenreID> genreIDS);
}

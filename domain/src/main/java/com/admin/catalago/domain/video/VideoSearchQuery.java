package com.admin.catalago.domain.video;

import com.admin.catalago.domain.castmember.CastMemberID;
import com.admin.catalago.domain.category.CategoryID;
import com.admin.catalago.domain.genre.GenreID;

import java.util.Set;

public record VideoSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction,
        Set<CastMemberID> castMembers,
        Set<CategoryID> categories,
        Set<GenreID> genres
) {
}
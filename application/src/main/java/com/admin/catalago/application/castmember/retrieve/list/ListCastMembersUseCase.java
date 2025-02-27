package com.admin.catalago.application.castmember.retrieve.list;

import com.admin.catalago.application.UseCase;
import com.admin.catalago.domain.pagination.Pagination;
import com.admin.catalago.domain.pagination.SearchQuery;

public sealed abstract class ListCastMembersUseCase
        extends UseCase<SearchQuery, Pagination<CastMemberListOutput>>
        permits DefaultListCastMembersUseCase {
}
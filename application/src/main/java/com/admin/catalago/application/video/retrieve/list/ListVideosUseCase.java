package com.admin.catalago.application.video.retrieve.list;

import com.admin.catalago.application.UseCase;
import com.admin.catalago.domain.pagination.Pagination;
import com.admin.catalago.domain.video.VideoSearchQuery;

public abstract class ListVideosUseCase
        extends UseCase<VideoSearchQuery, Pagination<VideoListOutput>> {
}
package com.admin.catalago.domain.video;

import com.admin.catalago.domain.resource.Resource;

public record VideoResource(
        VideoMediaType type,
        Resource resource
) {

    public static VideoResource with(final VideoMediaType type, final Resource resource) {
        return new VideoResource(type, resource);
    }
}
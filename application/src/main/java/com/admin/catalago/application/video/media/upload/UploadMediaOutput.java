package com.admin.catalago.application.video.media.upload;

import com.admin.catalago.domain.video.Video;
import com.admin.catalago.domain.video.VideoMediaType;

public record UploadMediaOutput(
        String videoId,
        VideoMediaType mediaType
) {

    public static UploadMediaOutput with(final Video aVideo, final VideoMediaType aType) {
        return new UploadMediaOutput(aVideo.getId().getValue(), aType);
    }
}
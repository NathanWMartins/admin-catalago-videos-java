package com.admin.catalago.application.video.create;

import com.admin.catalago.domain.video.Video;

public record CreateVideoOutput(String id) {

    public static CreateVideoOutput from(final Video aVideo) {
        return new CreateVideoOutput(aVideo.getId().getValue());
    }
}
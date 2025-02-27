package com.admin.catalago.application.video.retrieve.get;

import com.admin.catalago.domain.exceptions.NotFoundException;
import com.admin.catalago.domain.video.Video;
import com.admin.catalago.domain.video.VideoGateway;
import com.admin.catalago.domain.video.VideoID;

import java.util.Objects;

public class DefaultGetVideoByIdUseCase extends GetVideoByIdUseCase {

    private final VideoGateway videoGateway;

    public DefaultGetVideoByIdUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Override
    public VideoOutput execute(final String anIn) {
        final var aVideoId = VideoID.from(anIn);
        return this.videoGateway.findById(aVideoId)
                .map(VideoOutput::from)
                .orElseThrow(() -> NotFoundException.with(Video.class, aVideoId));
    }
}
package com.admin.catalago.domain.video;

import java.util.Arrays;
import java.util.Optional;

public enum VideoMediaType{
    VIDEO,
    TRAILER,
    BANNER,
    THUMB,
    THUMBNAIL_HALF;

    public static Optional<VideoMediaType> of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst();
    }
}


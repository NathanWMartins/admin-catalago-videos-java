package com.admin.catalago.infrasctructure.video.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateVideoResponse(@JsonProperty("id") String id) {
}
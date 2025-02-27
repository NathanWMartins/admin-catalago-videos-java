package com.admin.catalago.infrasctructure.castmember.models;

import com.admin.catalago.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}

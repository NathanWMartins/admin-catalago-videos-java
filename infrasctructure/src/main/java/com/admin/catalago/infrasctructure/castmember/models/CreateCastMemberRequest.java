package com.admin.catalago.infrasctructure.castmember.models;

import com.admin.catalago.domain.castmember.CastMemberType;

public record CreateCastMemberRequest(String name, CastMemberType type) {
}

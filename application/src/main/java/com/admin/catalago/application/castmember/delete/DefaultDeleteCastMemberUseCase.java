package com.admin.catalago.application.castmember.delete;

import com.admin.catalago.domain.castmember.CastMemberGateway;
import com.admin.catalago.domain.castmember.CastMemberID;

import java.util.Objects;

public non-sealed class DefaultDeleteCastMemberUseCase extends DeleteCastMemberUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultDeleteCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public void execute(final String anIn) {
        this.castMemberGateway.deleteById(CastMemberID.from(anIn));
    }
}
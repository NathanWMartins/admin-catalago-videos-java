package com.admin.catalago.application.castmember.update;

import com.admin.catalago.domain.Identifier;
import com.admin.catalago.domain.castmember.CastMember;
import com.admin.catalago.domain.castmember.CastMemberGateway;
import com.admin.catalago.domain.castmember.CastMemberID;
import com.admin.catalago.domain.exceptions.NotFoundException;
import com.admin.catalago.domain.exceptions.NotificationExceptions;
import com.admin.catalago.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public non-sealed class DefaultUpdateCastMemberUseCase extends UpdateCastMemberUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultUpdateCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public UpdateCastMemberOutput execute(final UpdateCastMemberCommand aCommand) {
        final var anId = CastMemberID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aType = aCommand.type();

        final var aMember = this.castMemberGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() -> aMember.update(aName, aType));

        if (notification.hasError()) {
            notify(anId, notification);
        }

        return UpdateCastMemberOutput.from(this.castMemberGateway.update(aMember));
    }

    private void notify(final Identifier anId, final Notification notification) {
        throw new NotificationExceptions("Could not update Aggregate CastMember %s".formatted(anId.getValue()), notification);
    }

    private Supplier<NotFoundException> notFound(final CastMemberID anId) {
        return () -> NotFoundException.with(CastMember.class, anId);
    }
}
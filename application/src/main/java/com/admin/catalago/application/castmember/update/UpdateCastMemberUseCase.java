package com.admin.catalago.application.castmember.update;

import com.admin.catalago.application.UseCase;

public sealed abstract class UpdateCastMemberUseCase
        extends UseCase<UpdateCastMemberCommand, UpdateCastMemberOutput>
        permits DefaultUpdateCastMemberUseCase {
}
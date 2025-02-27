package com.admin.catalago.application.castmember.create;

import com.admin.catalago.application.UseCase;

public sealed abstract class CreateCastMemberUseCase
        extends UseCase<CreateCastMemberCommand, CreateCastMemberOutput>
        permits DefaultCreateCastMemberUseCase {
}

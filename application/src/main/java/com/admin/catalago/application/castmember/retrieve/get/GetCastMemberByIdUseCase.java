package com.admin.catalago.application.castmember.retrieve.get;

import com.admin.catalago.application.UseCase;

public sealed abstract class GetCastMemberByIdUseCase
        extends UseCase<String, CastMemberOutput>
        permits DefaultGetCastMemberByIdUseCase {
}
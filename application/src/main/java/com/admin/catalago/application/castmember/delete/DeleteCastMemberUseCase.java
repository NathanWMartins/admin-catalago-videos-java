package com.admin.catalago.application.castmember.delete;


import com.admin.catalago.application.UnitUseCase;

public sealed abstract class DeleteCastMemberUseCase
        extends UnitUseCase<String>
        permits DefaultDeleteCastMemberUseCase {
}
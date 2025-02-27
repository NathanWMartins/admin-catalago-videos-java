package com.admin.catalago.infrasctructure.configuration.usecases;

import com.admin.catalago.application.castmember.create.CreateCastMemberUseCase;
import com.admin.catalago.application.castmember.create.DefaultCreateCastMemberUseCase;
import com.admin.catalago.application.castmember.delete.DefaultDeleteCastMemberUseCase;
import com.admin.catalago.application.castmember.delete.DeleteCastMemberUseCase;
import com.admin.catalago.application.castmember.retrieve.get.DefaultGetCastMemberByIdUseCase;
import com.admin.catalago.application.castmember.retrieve.get.GetCastMemberByIdUseCase;
import com.admin.catalago.application.castmember.retrieve.list.DefaultListCastMembersUseCase;
import com.admin.catalago.application.castmember.retrieve.list.ListCastMembersUseCase;
import com.admin.catalago.application.castmember.update.DefaultUpdateCastMemberUseCase;
import com.admin.catalago.application.castmember.update.UpdateCastMemberUseCase;
import com.admin.catalago.domain.castmember.CastMemberGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CastMemberUseCaseConfig {

    private final CastMemberGateway castMemberGateway;

    public CastMemberUseCaseConfig(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Bean
    public CreateCastMemberUseCase createCastMemberUseCase() {
        return new DefaultCreateCastMemberUseCase(castMemberGateway);
    }

    @Bean
    public DeleteCastMemberUseCase deleteCastMemberUseCase() {
        return new DefaultDeleteCastMemberUseCase(castMemberGateway);
    }

    @Bean
    public GetCastMemberByIdUseCase getCastMemberByIdUseCase() {
        return new DefaultGetCastMemberByIdUseCase(castMemberGateway);
    }

    @Bean
    public ListCastMembersUseCase listCastMembersUseCase() {
        return new DefaultListCastMembersUseCase(castMemberGateway);
    }

    @Bean
    public UpdateCastMemberUseCase updateCastMemberUseCase() {
        return new DefaultUpdateCastMemberUseCase(castMemberGateway);
    }
}

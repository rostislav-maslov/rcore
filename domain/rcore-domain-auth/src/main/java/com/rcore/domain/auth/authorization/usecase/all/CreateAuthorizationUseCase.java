package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationIdGenerator;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.authorization.usecase.all.commands.CreateAuthorizationCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAuthorizationUseCase {
    private final AuthorizationRepository authorizationRepository;
    private final AuthorizationIdGenerator authorizationIdGenerator;

    public AuthorizationEntity create(CreateAuthorizationCommand createAuthorizationCommand) {
        AuthorizationEntity authorizationEntity = new AuthorizationEntity();
        authorizationEntity.setId(authorizationIdGenerator.generate());
        authorizationEntity.setAuthorizationData(createAuthorizationCommand.getAuthorizationData());
        authorizationEntity.setType(createAuthorizationCommand.getType());
        authorizationEntity.setStatus(createAuthorizationCommand.getStatus());
        authorizationEntity.setRejectionCause(createAuthorizationCommand.getRejectionCause());
        authorizationEntity.setAccessTokenId(createAuthorizationCommand.getAccessTokenId());
        authorizationEntity.setRefreshTokenId(createAuthorizationCommand.getRefreshTokenId());
        authorizationEntity = authorizationRepository.save(authorizationEntity);
        return authorizationEntity;
    }
}

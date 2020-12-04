package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.exceptions.AuthorizationNotFoundException;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.authorization.usecase.all.commands.SuccessfulAuthorizationCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuccessfulAuthorizationUseCase {

    private final AuthorizationRepository authorizationRepository;

    public AuthorizationEntity successAuthorization(SuccessfulAuthorizationCommand successfulAuthorizationCommand) throws AuthorizationNotFoundException {

        AuthorizationEntity authorizationEntity = authorizationRepository.findById(successfulAuthorizationCommand.getAuthorizationId())
                .orElseThrow(AuthorizationNotFoundException::new);

        authorizationEntity.setSuccess(successfulAuthorizationCommand.getAccessTokeId(), successfulAuthorizationCommand.getRefreshTokeId());
        authorizationEntity = authorizationRepository.save(authorizationEntity);
        return authorizationEntity;
    }
}

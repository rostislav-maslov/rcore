package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;

public class FindAuthorizationByIdUseCase extends AbstractFindByIdUseCase<String, AuthorizationEntity, AuthorizationRepository> {

    public FindAuthorizationByIdUseCase(AuthorizationRepository repository) {
        super(repository);
    }
}

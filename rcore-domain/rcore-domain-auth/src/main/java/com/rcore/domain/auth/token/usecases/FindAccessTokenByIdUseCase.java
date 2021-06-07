package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;

public class FindAccessTokenByIdUseCase extends AbstractFindByIdUseCase<String, AccessTokenEntity, AccessTokenRepository> {

    public FindAccessTokenByIdUseCase(AccessTokenRepository repository) {
        super(repository);
    }
}

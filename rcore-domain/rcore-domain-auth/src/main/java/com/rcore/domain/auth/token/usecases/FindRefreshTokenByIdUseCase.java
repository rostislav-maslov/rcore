package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;

public class FindRefreshTokenByIdUseCase extends AbstractFindByIdUseCase<String, RefreshTokenEntity, RefreshTokenRepository> {

    public FindRefreshTokenByIdUseCase(RefreshTokenRepository repository) {
        super(repository);
    }
}

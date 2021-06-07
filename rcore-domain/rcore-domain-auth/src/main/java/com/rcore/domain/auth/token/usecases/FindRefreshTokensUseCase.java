package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.auth.token.port.filter.AccessTokenFilters;
import com.rcore.domain.auth.token.port.filter.RefreshTokenFilters;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;

public class FindRefreshTokensUseCase extends AbstractFindWithFiltersUseCase<RefreshTokenEntity, RefreshTokenFilters, RefreshTokenRepository> {

    public FindRefreshTokensUseCase(RefreshTokenRepository repository) {
        super(repository);
    }
}

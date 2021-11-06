package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.auth.token.port.filter.AccessTokenFilters;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;

public class FindAccessTokensUseCase extends AbstractFindWithFiltersUseCase<AccessTokenEntity, AccessTokenFilters, AccessTokenRepository> {

    public FindAccessTokensUseCase(AccessTokenRepository repository) {
        super(repository);
    }
}

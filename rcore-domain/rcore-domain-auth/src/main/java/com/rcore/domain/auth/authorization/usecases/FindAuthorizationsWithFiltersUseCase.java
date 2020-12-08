package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;

public class FindAuthorizationsWithFiltersUseCase extends AbstractFindWithFiltersUseCase<AuthorizationEntity, SearchFilters, AuthorizationRepository> {

    public FindAuthorizationsWithFiltersUseCase(AuthorizationRepository repository) {
        super(repository);
    }
}

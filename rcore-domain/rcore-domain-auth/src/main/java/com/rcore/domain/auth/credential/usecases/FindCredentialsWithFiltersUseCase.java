package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;

public class FindCredentialsWithFiltersUseCase extends AbstractFindWithFiltersUseCase<CredentialEntity, SearchFilters, CredentialRepository> {

    public FindCredentialsWithFiltersUseCase(CredentialRepository repository) {
        super(repository);
    }
}

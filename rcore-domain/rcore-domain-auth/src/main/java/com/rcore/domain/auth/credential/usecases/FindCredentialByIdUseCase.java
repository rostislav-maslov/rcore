package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;

public class FindCredentialByIdUseCase extends AbstractFindByIdUseCase<String, CredentialEntity, CredentialRepository> {

    public FindCredentialByIdUseCase(CredentialRepository repository) {
        super(repository);
    }
}

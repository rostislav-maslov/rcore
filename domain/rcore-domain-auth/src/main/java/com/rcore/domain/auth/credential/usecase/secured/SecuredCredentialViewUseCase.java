package com.rcore.domain.auth.credential.usecase.secured;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.security.port.CredentialVerifier;
import com.rcore.domain.security.usecase.secured.SecuredViewUseCase;

public class SecuredCredentialViewUseCase extends SecuredViewUseCase<String, CredentialEntity, SearchFilters, CredentialRepository> {

    public SecuredCredentialViewUseCase(CredentialVerifier credentialVerifier, CredentialRepository repository) {
        super(credentialVerifier, repository);
    }
}

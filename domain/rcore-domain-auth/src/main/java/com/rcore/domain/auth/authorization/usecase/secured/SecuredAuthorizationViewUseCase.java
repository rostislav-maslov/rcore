package com.rcore.domain.auth.authorization.usecase.secured;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.security.port.CredentialVerifier;
import com.rcore.domain.security.usecase.secured.SecuredViewUseCase;

public class SecuredAuthorizationViewUseCase extends SecuredViewUseCase<String, AuthorizationEntity, SearchFilters, AuthorizationRepository> {

    public SecuredAuthorizationViewUseCase(CredentialVerifier credentialVerifier, AuthorizationRepository repository) {
        super(credentialVerifier, repository);
    }
}

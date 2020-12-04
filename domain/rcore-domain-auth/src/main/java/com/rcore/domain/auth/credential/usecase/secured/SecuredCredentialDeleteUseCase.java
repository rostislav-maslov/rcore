package com.rcore.domain.auth.credential.usecase.secured;

import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.security.port.CredentialVerifier;
import com.rcore.domain.security.usecase.secured.SecuredDeleteUseCase;

public class SecuredCredentialDeleteUseCase extends SecuredDeleteUseCase<String, CredentialRepository> {

    public SecuredCredentialDeleteUseCase(CredentialVerifier credentialVerifier, CredentialRepository repository) {
        super(credentialVerifier, repository);
    }

    
}

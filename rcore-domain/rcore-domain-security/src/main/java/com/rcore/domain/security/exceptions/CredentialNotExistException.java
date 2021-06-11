package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class CredentialNotExistException extends UnauthorizedDomainException {

    public CredentialNotExistException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.CREDENTIAL_NOT_EXIST.name(),
                "Credential not found in storage"
        ));
    }
}

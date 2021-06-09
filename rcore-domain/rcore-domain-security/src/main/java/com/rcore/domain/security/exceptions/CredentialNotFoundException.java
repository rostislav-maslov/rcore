package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialNotFoundException extends DomainException {

    public CredentialNotFoundException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.CREDENTIAL_NOT_FOUND.name(),
                "Credential not found"
        ));
    }
}

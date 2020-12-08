package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialStatusIsRequiredException extends DomainException {

    public CredentialStatusIsRequiredException() {
        super("Credential status is required");
    }
}

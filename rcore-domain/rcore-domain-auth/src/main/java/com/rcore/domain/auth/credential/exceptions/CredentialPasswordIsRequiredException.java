package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialPasswordIsRequiredException extends DomainException {

    public CredentialPasswordIsRequiredException() {
        super("Credential password is required");
    }
}

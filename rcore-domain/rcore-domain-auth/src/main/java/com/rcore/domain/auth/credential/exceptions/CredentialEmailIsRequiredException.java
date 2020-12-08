package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialEmailIsRequiredException extends DomainException {

    public CredentialEmailIsRequiredException() {
        super("Credential email is required");
    }
}

package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialRoleIsRequiredException extends DomainException {

    public CredentialRoleIsRequiredException() {
        super("Credential role is required");
    }
}

package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class AuthenticatedCredentialIsBlockedException extends DomainException {

    public AuthenticatedCredentialIsBlockedException() {
        super("Authenticated credential is blocked");
    }
}

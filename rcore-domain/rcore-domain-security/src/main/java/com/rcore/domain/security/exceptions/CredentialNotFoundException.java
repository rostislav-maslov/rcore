package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialNotFoundException extends DomainException {

    public CredentialNotFoundException() {
        super("Credential not found");
    }
}

package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialNotFoundException extends DomainException {

    public CredentialNotFoundException() {
        super("Credential not found");
    }

    public CredentialNotFoundException(String id) {
        super("Credential not found by ID: " + id);
    }
}

package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public abstract class CredentialDomainException extends DomainException {

    public CredentialDomainException(String message) {
        super(message, "Credential");
    }
}

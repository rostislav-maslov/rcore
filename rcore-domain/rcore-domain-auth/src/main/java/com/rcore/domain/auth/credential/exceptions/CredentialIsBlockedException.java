package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialIsBlockedException extends DomainException {

    public CredentialIsBlockedException(String credentialId) {
        super("Credential with ID=" + credentialId +" is blocked");
    }
}

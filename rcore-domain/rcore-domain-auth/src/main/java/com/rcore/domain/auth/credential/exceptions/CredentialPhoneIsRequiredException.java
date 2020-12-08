package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialPhoneIsRequiredException extends DomainException {

    public CredentialPhoneIsRequiredException() {
        super("Credential phone is required");
    }
}

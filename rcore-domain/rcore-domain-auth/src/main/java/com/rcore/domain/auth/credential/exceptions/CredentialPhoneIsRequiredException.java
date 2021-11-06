package com.rcore.domain.auth.credential.exceptions;

public class CredentialPhoneIsRequiredException extends CredentialDomainException {

    public CredentialPhoneIsRequiredException() {
        super("Credential phone is required");
    }
}

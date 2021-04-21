package com.rcore.domain.auth.credential.exceptions;

public class CredentialEmailIsRequiredException extends CredentialDomainException {

    public CredentialEmailIsRequiredException() {
        super("Credential email is required");
    }
}

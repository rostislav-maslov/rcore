package com.rcore.domain.auth.credential.exceptions;

public class CredentialPasswordIsRequiredException extends CredentialDomainException {

    public CredentialPasswordIsRequiredException() {
        super("Credential password is required");
    }
}

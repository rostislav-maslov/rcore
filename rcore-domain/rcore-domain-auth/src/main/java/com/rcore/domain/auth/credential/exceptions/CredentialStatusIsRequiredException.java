package com.rcore.domain.auth.credential.exceptions;

public class CredentialStatusIsRequiredException extends CredentialDomainException {

    public CredentialStatusIsRequiredException() {
        super("Credential status is required");
    }
}

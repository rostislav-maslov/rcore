package com.rcore.domain.auth.credential.exceptions;

public class CredentialRoleIsRequiredException extends CredentialDomainException {

    public CredentialRoleIsRequiredException() {
        super("Credential role is required");
    }
}

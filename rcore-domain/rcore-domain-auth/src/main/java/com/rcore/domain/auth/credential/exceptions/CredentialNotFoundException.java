package com.rcore.domain.auth.credential.exceptions;

public class CredentialNotFoundException extends CredentialDomainException {

    public CredentialNotFoundException() {
        super("Credential not found");
    }

    public CredentialNotFoundException(String id) {
        super("Credential not found by ID: " + id);
    }
}

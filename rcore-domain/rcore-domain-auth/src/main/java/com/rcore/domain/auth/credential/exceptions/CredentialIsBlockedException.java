package com.rcore.domain.auth.credential.exceptions;

public class CredentialIsBlockedException extends CredentialDomainException {

    public CredentialIsBlockedException(String credentialId) {
        super("Credential with ID=" + credentialId +" is blocked");
    }
}

package com.rcore.domain.auth.credential.exceptions;

public class CredentialWithEmailAlreadyExistException extends CredentialDomainException {

    public CredentialWithEmailAlreadyExistException(String email) {
        super("Credential with " + email + " email already exist");
    }
}

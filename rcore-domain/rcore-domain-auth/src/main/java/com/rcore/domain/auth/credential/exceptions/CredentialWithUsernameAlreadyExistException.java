package com.rcore.domain.auth.credential.exceptions;

public class CredentialWithUsernameAlreadyExistException extends CredentialDomainException {

    public CredentialWithUsernameAlreadyExistException(String username) {
        super("Credential with " + username + " username already exist");
    }
}

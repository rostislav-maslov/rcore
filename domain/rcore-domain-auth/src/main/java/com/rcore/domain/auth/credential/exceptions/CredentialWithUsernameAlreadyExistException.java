package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialWithUsernameAlreadyExistException extends DomainException {

    public CredentialWithUsernameAlreadyExistException(String username) {
        super("Credential with " + username + " username already exist");
    }
}

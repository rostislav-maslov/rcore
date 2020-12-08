package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialWithEmailAlreadyExistException extends DomainException {

    public CredentialWithEmailAlreadyExistException(String email) {
        super("Credential with " + email + " email already exist");
    }
}

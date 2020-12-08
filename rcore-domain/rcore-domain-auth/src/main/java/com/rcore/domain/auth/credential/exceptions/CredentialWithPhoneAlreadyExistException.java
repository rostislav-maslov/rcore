package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class CredentialWithPhoneAlreadyExistException extends DomainException {

    public CredentialWithPhoneAlreadyExistException(String phone) {
        super("Credential with " + phone + " phone already exist");
    }
}

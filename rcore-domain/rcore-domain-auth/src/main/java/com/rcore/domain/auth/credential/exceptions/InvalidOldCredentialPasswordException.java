package com.rcore.domain.auth.credential.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class InvalidOldCredentialPasswordException extends DomainException {

    public InvalidOldCredentialPasswordException() {
        super("Invalid old credential password");
    }
}

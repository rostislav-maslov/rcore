package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class InvalidPasswordException extends DomainException {

    public InvalidPasswordException() {
        super("Invalid password");
    }
}

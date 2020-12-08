package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class InvalidUsernameException extends DomainException {

    public InvalidUsernameException(String username) {
        super("Invalid username: " + username);
    }
}

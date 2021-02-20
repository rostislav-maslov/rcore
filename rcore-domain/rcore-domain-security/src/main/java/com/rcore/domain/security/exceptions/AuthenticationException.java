package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class AuthenticationException extends DomainException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super("Authentication exception");
    }
}

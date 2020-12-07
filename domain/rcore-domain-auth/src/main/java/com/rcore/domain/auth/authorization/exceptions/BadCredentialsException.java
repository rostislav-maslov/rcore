package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class BadCredentialsException extends DomainException {

    public BadCredentialsException() {
        super("Bad credential");
    }

    public BadCredentialsException(String credential) {
        super("Bad credential: " + credential);
    }
}

package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public abstract class AuthorizationDomainException extends DomainException {

    public AuthorizationDomainException(String message) {
        super(message, "Auth");
    }
}

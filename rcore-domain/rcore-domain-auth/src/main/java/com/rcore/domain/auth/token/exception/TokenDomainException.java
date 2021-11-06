package com.rcore.domain.auth.token.exception;

import com.rcore.domain.commons.exception.DomainException;

public abstract class TokenDomainException extends DomainException {

    public TokenDomainException(String message) {
        super(message, "Token");
    }
}

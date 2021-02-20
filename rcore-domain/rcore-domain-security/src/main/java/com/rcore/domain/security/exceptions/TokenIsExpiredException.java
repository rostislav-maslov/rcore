package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class TokenIsExpiredException extends DomainException {

    public TokenIsExpiredException() {
        super("Access token is expired");
    }

    public TokenIsExpiredException(String id) {
        super("Access token " + id + " is expired");
    }
}

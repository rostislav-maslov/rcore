package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class TokenIsExpiredException extends DomainException {

    public TokenIsExpiredException(String id) {
        super("Access token " + id + " is expired");
    }
}

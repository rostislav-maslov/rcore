package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class TokenIsRequiredException extends DomainException {

    public TokenIsRequiredException() {
        super("Unauthenticated");
    }
}

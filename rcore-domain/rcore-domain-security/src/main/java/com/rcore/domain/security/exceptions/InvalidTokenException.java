package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class InvalidTokenException extends DomainException {

    public InvalidTokenException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.INVALID_TOKEN.name(),
                "Token has bean modified"
        ));
    }
}

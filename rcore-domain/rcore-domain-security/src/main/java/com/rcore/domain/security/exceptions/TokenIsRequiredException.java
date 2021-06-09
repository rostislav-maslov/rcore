package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class TokenIsRequiredException extends DomainException {

    public TokenIsRequiredException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.TOKEN_IS_REQUIRED.name(),
                "Token is required"
        ));
    }
}

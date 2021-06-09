package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class TokenIsExpiredException extends DomainException {

    public TokenIsExpiredException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.TOKEN_IS_EXPIRED.name(),
                "Access token is expired"
        ));
    }

    public TokenIsExpiredException(String id) {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.TOKEN_IS_EXPIRED.name(),
                "Access token " + id + " is expired"
        ));
    }
}

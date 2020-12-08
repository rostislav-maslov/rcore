package com.rcore.domain.auth.token.exception;

import com.rcore.domain.commons.exception.DomainException;

public class RefreshTokenIsExpiredException extends DomainException {

    public RefreshTokenIsExpiredException(String id) {
        super("Refresh token " + id + " is expired");
    }
}

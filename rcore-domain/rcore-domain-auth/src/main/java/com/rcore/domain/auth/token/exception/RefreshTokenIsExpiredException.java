package com.rcore.domain.auth.token.exception;

public class RefreshTokenIsExpiredException extends TokenDomainException {

    public RefreshTokenIsExpiredException(String id) {
        super("Refresh token " + id + " is expired");
    }
}

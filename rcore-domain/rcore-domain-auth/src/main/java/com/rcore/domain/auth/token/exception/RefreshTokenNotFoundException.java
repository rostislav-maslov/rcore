package com.rcore.domain.auth.token.exception;

public class RefreshTokenNotFoundException extends TokenDomainException {

    public RefreshTokenNotFoundException() {
        super("Refresh token not found");
    }

    public RefreshTokenNotFoundException(String id) {
        super("Refresh token not found by ID: " + id);
    }
}

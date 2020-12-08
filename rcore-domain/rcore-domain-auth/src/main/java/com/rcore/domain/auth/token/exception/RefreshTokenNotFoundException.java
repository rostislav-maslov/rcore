package com.rcore.domain.auth.token.exception;

import com.rcore.domain.commons.exception.DomainException;

public class RefreshTokenNotFoundException extends DomainException {

    public RefreshTokenNotFoundException() {
        super("Refresh token not found");
    }

    public RefreshTokenNotFoundException(String id) {
        super("Refresh token not found by ID: " + id);
    }
}

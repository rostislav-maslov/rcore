package com.rcore.domain.auth.token.exception;

public class AccessTokenNotFoundException extends TokenDomainException {

    public AccessTokenNotFoundException() {
        super("Access token not found");
    }

    public AccessTokenNotFoundException(String id) {
        super("Access token not found by ID: " + id);
    }
}

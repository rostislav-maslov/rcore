package com.rcore.domain.auth.token.exception;

import com.rcore.domain.commons.exception.DomainException;

public class AccessTokenNotFoundException extends DomainException {

    public AccessTokenNotFoundException() {
        super("Access token not found");
    }

    public AccessTokenNotFoundException(String id) {
        super("Access token not found by ID: " + id);
    }
}

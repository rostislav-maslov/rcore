package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class AuthorizationNotFoundException extends DomainException {

    public AuthorizationNotFoundException(String id) {
        super("Authorization not found by ID: " + id);
    }
}

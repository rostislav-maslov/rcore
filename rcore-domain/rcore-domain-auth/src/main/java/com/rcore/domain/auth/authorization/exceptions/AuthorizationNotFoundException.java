package com.rcore.domain.auth.authorization.exceptions;

public class AuthorizationNotFoundException extends AuthorizationDomainException {

    public AuthorizationNotFoundException(String id) {
        super("Authorization not found by ID: " + id);
    }
}

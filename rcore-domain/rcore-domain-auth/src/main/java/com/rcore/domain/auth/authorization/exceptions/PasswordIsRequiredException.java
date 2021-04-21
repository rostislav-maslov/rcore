package com.rcore.domain.auth.authorization.exceptions;

public class PasswordIsRequiredException extends AuthorizationDomainException {

    public PasswordIsRequiredException() {
        super("Password is required");
    }
}

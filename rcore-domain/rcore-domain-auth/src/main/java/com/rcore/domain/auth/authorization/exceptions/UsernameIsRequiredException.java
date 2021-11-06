package com.rcore.domain.auth.authorization.exceptions;

public class UsernameIsRequiredException extends AuthorizationDomainException {

    public UsernameIsRequiredException() {
        super("Username is required");
    }
}

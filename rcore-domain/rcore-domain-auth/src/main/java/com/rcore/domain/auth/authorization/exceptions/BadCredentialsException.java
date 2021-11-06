package com.rcore.domain.auth.authorization.exceptions;

public class BadCredentialsException extends AuthorizationDomainException {

    public BadCredentialsException() {
        super("Bad credential");
    }

    public BadCredentialsException(String credential) {
        super("Bad credential: " + credential);
    }
}

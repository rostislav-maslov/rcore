package com.rcore.domain.auth.authorization.exceptions;

public class AddressIsRequiredException extends AuthorizationDomainException {

    public AddressIsRequiredException() {
        super("Address is required");
    }
}

package com.rcore.domain.auth.authorization.exceptions;

public class ConfirmationCodeIsRequiredException extends AuthorizationDomainException {

    public ConfirmationCodeIsRequiredException() {
        super("Confirmation code is required");
    }
}

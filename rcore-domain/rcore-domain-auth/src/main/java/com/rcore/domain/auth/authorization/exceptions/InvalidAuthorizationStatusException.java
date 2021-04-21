package com.rcore.domain.auth.authorization.exceptions;

public class InvalidAuthorizationStatusException extends AuthorizationDomainException {

    public InvalidAuthorizationStatusException(String actualStatus) {
        super("Invalid authorization status. Actual status: " + actualStatus);
    }
}

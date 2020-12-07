package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class InvalidAuthorizationStatusException extends DomainException {

    public InvalidAuthorizationStatusException(String actualStatus) {
        super("Invalid authorization status. Actual status: " + actualStatus);
    }
}

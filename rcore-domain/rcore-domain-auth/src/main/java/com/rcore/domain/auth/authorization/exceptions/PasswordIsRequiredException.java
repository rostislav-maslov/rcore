package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class PasswordIsRequiredException extends DomainException {

    public PasswordIsRequiredException() {
        super("Password is required");
    }
}

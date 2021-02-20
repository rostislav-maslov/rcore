package com.rcore.domain.auth.passwordRecovery.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class PasswordRecoveryNotFoundException extends DomainException {

    public PasswordRecoveryNotFoundException() {
        super("Password recovery not found");
    }

    public PasswordRecoveryNotFoundException(String id) {
        super("Password recovery not found by ID: " + id);
    }
}

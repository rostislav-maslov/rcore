package com.rcore.domain.auth.passwordRecovery.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public abstract class PasswordRecoveryDomainException extends DomainException {

    public PasswordRecoveryDomainException(String message) {
        super(message, "Password recovery");
    }
}

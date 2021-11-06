package com.rcore.domain.auth.confirmationCode.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public abstract class ConfirmationCodeDomainException extends DomainException {

    public ConfirmationCodeDomainException(String message) {
        super(message);
    }
}

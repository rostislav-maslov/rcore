package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ConfirmationCodeIsRequiredException extends DomainException {

    public ConfirmationCodeIsRequiredException() {
        super("Confirmation code is required");
    }
}

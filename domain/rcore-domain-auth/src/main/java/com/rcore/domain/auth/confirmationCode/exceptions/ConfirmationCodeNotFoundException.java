package com.rcore.domain.auth.confirmationCode.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ConfirmationCodeNotFoundException extends DomainException {

    public ConfirmationCodeNotFoundException() {
        super("Confirmation code not found");
    }

    public ConfirmationCodeNotFoundException(String id) {
        super("Confirmation code not found by ID: " + id);
    }
}

package com.rcore.domain.auth.confirmationCode.exceptions;

public class ConfirmationCodeNotFoundException extends ConfirmationCodeDomainException {

    public ConfirmationCodeNotFoundException() {
        super("Confirmation code not found");
    }

    public ConfirmationCodeNotFoundException(String id) {
        super("Confirmation code not found by ID: " + id);
    }
}

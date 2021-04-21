package com.rcore.domain.auth.passwordRecovery.exceptions;

public class PasswordRecoveryNotFoundException extends PasswordRecoveryDomainException {

    public PasswordRecoveryNotFoundException() {
        super("Password recovery not found");
    }

    public PasswordRecoveryNotFoundException(String id) {
        super("Password recovery not found by ID: " + id);
    }
}

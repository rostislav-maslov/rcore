package com.rcore.domain.auth.confirmationCode.exceptions;

public class ExistNotConfirmedCodeException extends ConfirmationCodeDomainException {
    public ExistNotConfirmedCodeException(String authorizationId) {
        super("Exist not confirmed code for authorization " + authorizationId);
    }
}

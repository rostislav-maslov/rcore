package com.rcore.domain.auth.confirmationCode.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ExistNotConfirmedCodeException extends DomainException {
    public ExistNotConfirmedCodeException(String authorizationId) {
        super("Exist not confirmed code for authorization " + authorizationId);
    }
}

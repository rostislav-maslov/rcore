package com.rcore.domain.auth.confirmationCode.exceptions;

import com.rcore.domain.commons.exception.DomainException;

import java.time.LocalDateTime;

public class ConfirmationCodeIsExpiredException extends DomainException {

    public ConfirmationCodeIsExpiredException(String code, LocalDateTime expiredAt) {
        super("Confirmation code " + code + " is expired at " + expiredAt.toString());
    }
}

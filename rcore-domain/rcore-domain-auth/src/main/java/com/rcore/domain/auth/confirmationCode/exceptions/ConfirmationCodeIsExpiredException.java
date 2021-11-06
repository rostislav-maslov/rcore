package com.rcore.domain.auth.confirmationCode.exceptions;

import java.time.LocalDateTime;

public class ConfirmationCodeIsExpiredException extends ConfirmationCodeDomainException {

    public ConfirmationCodeIsExpiredException(String code, LocalDateTime expiredAt) {
        super("Confirmation code " + code + " is expired at " + expiredAt.toString());
    }
}

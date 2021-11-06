package com.rcore.domain.auth.confirmationCode.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

public class ExistNotConfirmedCodeException extends ConfirmationCodeDomainException {

    @Getter
    private LocalDateTime expiredDate;

    public ExistNotConfirmedCodeException(String authorizationId) {
        super("Exist not confirmed code for authorization " + authorizationId);
    }

    public ExistNotConfirmedCodeException(String authorizationId, LocalDateTime expiredDate) {
        super("Exist not confirmed code for authorization " + authorizationId);
        this.expiredDate = expiredDate;
    }
}

package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ConvertingTokenException extends DomainException {

    public ConvertingTokenException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.CONVERTING_TOKEN_ERROR.name(),
                "Failed converting token"
        ));
    }
}

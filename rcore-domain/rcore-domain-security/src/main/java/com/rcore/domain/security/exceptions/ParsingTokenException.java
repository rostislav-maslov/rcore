package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ParsingTokenException extends DomainException {

    public ParsingTokenException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.PARSING_TOKEN_ERROR.name(),
                "Invalid token format"
        ));
    }
}

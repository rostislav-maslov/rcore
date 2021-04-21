package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ParsingTokenException extends DomainException {

    public ParsingTokenException() {
        super("Invalid token format");
    }
}

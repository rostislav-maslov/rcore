package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ConvertingTokenException extends DomainException {

    public ConvertingTokenException() {
        super("Failed converting token");
    }
}

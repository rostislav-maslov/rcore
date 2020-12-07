package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class SendingTypeIsRequiredException extends DomainException {

    public SendingTypeIsRequiredException() {
        super("Sending type is required");
    }
}

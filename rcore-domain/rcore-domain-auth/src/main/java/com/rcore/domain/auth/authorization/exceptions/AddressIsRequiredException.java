package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class AddressIsRequiredException extends DomainException {

    public AddressIsRequiredException() {
        super("Address is required");
    }
}

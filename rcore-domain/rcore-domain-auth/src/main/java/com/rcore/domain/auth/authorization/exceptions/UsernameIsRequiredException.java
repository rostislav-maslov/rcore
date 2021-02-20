package com.rcore.domain.auth.authorization.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class UsernameIsRequiredException extends DomainException {

    public UsernameIsRequiredException() {
        super("Username is required");
    }
}

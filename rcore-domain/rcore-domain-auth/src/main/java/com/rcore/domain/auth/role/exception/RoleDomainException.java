package com.rcore.domain.auth.role.exception;

import com.rcore.domain.commons.exception.DomainException;

public abstract class RoleDomainException extends DomainException {

    public RoleDomainException(String message) {
        super(message, "Role");
    }
}

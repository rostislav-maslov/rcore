package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class AccessTokenNotExistException extends UnauthorizedDomainException {

    public AccessTokenNotExistException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.ACCESS_TOKEN_NOT_EXIST.name(),
                "Access token not found in storage"
        ));
    }
}

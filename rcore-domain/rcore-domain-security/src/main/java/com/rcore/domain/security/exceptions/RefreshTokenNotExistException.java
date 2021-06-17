package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class RefreshTokenNotExistException extends UnauthorizedDomainException {

    public RefreshTokenNotExistException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.REFRESH_TOKEN_NOT_EXIST.name(),
                "Refresh token not found in storage"
        ));
    }
}

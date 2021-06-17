package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class RefreshTokenMalformedException extends UnauthorizedDomainException {

    public RefreshTokenMalformedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.REFRESH_TOKEN_MALFORMED.name(),
                "Incorrect refresh token format"
        ));
    }
}

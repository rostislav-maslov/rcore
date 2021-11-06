package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class RefreshTokenExpiredException extends UnauthorizedDomainException {

    public RefreshTokenExpiredException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.REFRESH_TOKEN_EXPIRED.name(),
                "Refresh token has been expired"
        ));
    }
}

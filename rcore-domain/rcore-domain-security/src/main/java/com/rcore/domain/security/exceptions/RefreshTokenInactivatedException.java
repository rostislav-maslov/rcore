package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class RefreshTokenInactivatedException extends UnauthorizedDomainException {

    public RefreshTokenInactivatedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.REFRESH_TOKEN_INACTIVATED.name(),
                "Refresh token inactivated"
        ));
    }
}

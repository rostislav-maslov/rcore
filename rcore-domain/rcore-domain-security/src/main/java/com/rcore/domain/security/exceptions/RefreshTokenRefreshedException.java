package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class RefreshTokenRefreshedException extends UnauthorizedDomainException {

    public RefreshTokenRefreshedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.REFRESH_TOKEN_REFRESHED.name(),
                "Refresh token has been refreshed"
        ));
    }
}

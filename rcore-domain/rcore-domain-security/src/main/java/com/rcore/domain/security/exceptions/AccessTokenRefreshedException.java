package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class AccessTokenRefreshedException extends UnauthorizedDomainException {

    public AccessTokenRefreshedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.ACCESS_TOKEN_REFRESHED.name(),
                "Access token has been refreshed"
        ));
    }
}

package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class RefreshTokenNotProvided extends UnauthorizedDomainException {

    public RefreshTokenNotProvided() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.REFRESH_TOKEN_NOT_PROVIDED.name(),
                "Refresh token is required"
        ));
    }
}

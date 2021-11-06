package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class RefreshTokenModifiedException extends UnauthorizedDomainException {

    public RefreshTokenModifiedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.REFRESH_TOKEN_MODIFIED.name(),
                "Refresh token has been modified"
        ));
    }
}

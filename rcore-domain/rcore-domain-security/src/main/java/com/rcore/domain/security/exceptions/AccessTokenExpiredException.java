package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class AccessTokenExpiredException extends UnauthorizedDomainException {

    public AccessTokenExpiredException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.ACCESS_TOKEN_EXPIRED.name(),
                "Access token has been expired"
        ));
    }
}

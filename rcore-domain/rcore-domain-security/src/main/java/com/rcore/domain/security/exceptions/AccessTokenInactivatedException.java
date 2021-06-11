package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class AccessTokenInactivatedException extends UnauthorizedDomainException {

    public AccessTokenInactivatedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.ACCESS_TOKEN_INACTIVATED.name(),
                "Access token inactivated"
        ));
    }
}

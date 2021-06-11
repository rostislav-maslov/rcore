package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class AccessTokenNotProvidedException extends UnauthorizedDomainException {

    public AccessTokenNotProvidedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.ACCESS_TOKEN_NOT_PROVIDED.name(),
                "Access token is required"
        ));
    }
}

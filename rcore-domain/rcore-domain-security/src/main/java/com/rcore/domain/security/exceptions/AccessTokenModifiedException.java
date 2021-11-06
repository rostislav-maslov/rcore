package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class AccessTokenModifiedException extends UnauthorizedDomainException {

    public AccessTokenModifiedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.ACCESS_TOKEN_MODIFIED.name(),
                "Access token has been modified"
        ));
    }
}

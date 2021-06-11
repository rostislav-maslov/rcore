package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.UnauthorizedDomainException;

public class AccessTokenMalformedException extends UnauthorizedDomainException {

    public AccessTokenMalformedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.ACCESS_TOKEN_MALFORMED.name(),
                "Incorrect access token format"
        ));
    }
}

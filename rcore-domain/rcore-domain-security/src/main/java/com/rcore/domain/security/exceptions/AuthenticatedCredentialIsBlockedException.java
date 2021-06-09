package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class AuthenticatedCredentialIsBlockedException extends DomainException {

    public AuthenticatedCredentialIsBlockedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.CREDENTIAL_IS_BLOCKED.name(),
                "Authenticated credential is blocked"
        ));
    }
}

package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.ForbiddenDomainException;

public class CredentialBlockedException extends ForbiddenDomainException {

    public CredentialBlockedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.CREDENTIAL_BLOCKED.name(),
                "Credential is blocked"
        ));
    }
}

package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.ForbiddenDomainException;

public class CredentialPermissionInsufficientException extends ForbiddenDomainException {

    public CredentialPermissionInsufficientException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.CREDENTIAL_PERMISSIONS_INSUFFICIENT.name(),
                "Access denied"
        ));
    }
}

package com.rcore.domain.auth.credential.exceptions;

public class InvalidOldCredentialPasswordException extends CredentialDomainException {

    public InvalidOldCredentialPasswordException() {
        super("Invalid old credential password");
    }
}

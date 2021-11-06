package com.rcore.domain.auth.credential.exceptions;

public class CredentialWithPhoneAlreadyExistException extends CredentialDomainException {

    public CredentialWithPhoneAlreadyExistException(String phone) {
        super("Credential with " + phone + " phone already exist");
    }
}

package com.rcore.domain.auth.authorization.exceptions;

public class SendingTypeIsRequiredException extends AuthorizationDomainException {

    public SendingTypeIsRequiredException() {
        super("Sending type is required");
    }
}

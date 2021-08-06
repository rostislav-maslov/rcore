package com.rcore.domain.commons.exception;

import java.util.List;

public class InternalServerDomainException extends DomainException {

    public InternalServerDomainException() {
    }

    public InternalServerDomainException(String message) {
        super(message);
    }

    public InternalServerDomainException(String domain, String reason, String details) {
        super(domain, reason, details);
    }

    public InternalServerDomainException(Error error) {
        super(error);
    }

    public InternalServerDomainException(List<Error> errors) {
        super(errors);
    }

    public InternalServerDomainException(String message, List<Error> errors) {
        super(message, errors);
    }

    public InternalServerDomainException(String message, Throwable cause, List<Error> errors) {
        super(message, cause, errors);
    }

    public InternalServerDomainException(Throwable cause, List<Error> errors) {
        super(cause, errors);
    }

    public InternalServerDomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}

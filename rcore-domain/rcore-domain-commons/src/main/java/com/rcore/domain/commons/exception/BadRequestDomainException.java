package com.rcore.domain.commons.exception;

import java.util.List;

public class BadRequestDomainException extends DomainException {

    public BadRequestDomainException() {
    }

    public BadRequestDomainException(String message) {
        super(message);
    }

    public BadRequestDomainException(String domain, String reason, String details) {
        super(domain, reason, details);
    }

    public BadRequestDomainException(Error error) {
        super(error);
    }

    public BadRequestDomainException(List<Error> errors) {
        super(errors);
    }

    public BadRequestDomainException(String message, List<Error> errors) {
        super(message, errors);
    }

    public BadRequestDomainException(String message, Throwable cause, List<Error> errors) {
        super(message, cause, errors);
    }

    public BadRequestDomainException(Throwable cause, List<Error> errors) {
        super(cause, errors);
    }

    public BadRequestDomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}

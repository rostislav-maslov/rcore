package com.rcore.domain.commons.exception;

import java.util.List;

public class ForbiddenDomainException extends DomainException {
    public ForbiddenDomainException() {
    }

    public ForbiddenDomainException(String message) {
        super(message);
    }

    public ForbiddenDomainException(String domain, String reason, String details) {
        super(domain, reason, details);
    }

    public ForbiddenDomainException(Error error) {
        super(error);
    }

    public ForbiddenDomainException(List<Error> errors) {
        super(errors);
    }

    public ForbiddenDomainException(String message, List<Error> errors) {
        super(message, errors);
    }

    public ForbiddenDomainException(String message, Throwable cause, List<Error> errors) {
        super(message, cause, errors);
    }

    public ForbiddenDomainException(Throwable cause, List<Error> errors) {
        super(cause, errors);
    }

    public ForbiddenDomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}

package com.rcore.domain.commons.exception;

import java.util.List;

public class NotFoundDomainException extends DomainException {

    public NotFoundDomainException() {
    }

    public NotFoundDomainException(String message) {
        super(message);
    }

    public NotFoundDomainException(String domain, String reason, String details) {
        super(domain, reason, details);
    }

    public NotFoundDomainException(Error error) {
        super(error);
    }

    public NotFoundDomainException(List<Error> errors) {
        super(errors);
    }

    public NotFoundDomainException(String message, List<Error> errors) {
        super(message, errors);
    }

    public NotFoundDomainException(String message, Throwable cause, List<Error> errors) {
        super(message, cause, errors);
    }

    public NotFoundDomainException(Throwable cause, List<Error> errors) {
        super(cause, errors);
    }

    public NotFoundDomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}

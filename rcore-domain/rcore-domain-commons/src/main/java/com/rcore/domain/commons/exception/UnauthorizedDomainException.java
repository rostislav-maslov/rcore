package com.rcore.domain.commons.exception;

import java.util.List;

public class UnauthorizedDomainException extends DomainException {

    public UnauthorizedDomainException() {
    }

    public UnauthorizedDomainException(String message) {
        super(message);
    }

    public UnauthorizedDomainException(Error error) {
        super(error);
    }

    public UnauthorizedDomainException(String domain, String reason, String details) {
        super(domain, reason, details);
    }

    public UnauthorizedDomainException(List<Error> errors) {
        super(errors);
    }

    public UnauthorizedDomainException(String message, List<Error> errors) {
        super(message, errors);
    }

    public UnauthorizedDomainException(String message, Throwable cause, List<Error> errors) {
        super(message, cause, errors);
    }

    public UnauthorizedDomainException(Throwable cause, List<Error> errors) {
        super(cause, errors);
    }

    public UnauthorizedDomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}

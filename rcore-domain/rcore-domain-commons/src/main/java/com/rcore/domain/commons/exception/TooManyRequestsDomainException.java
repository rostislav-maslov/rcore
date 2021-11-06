package com.rcore.domain.commons.exception;

import java.util.List;

public class TooManyRequestsDomainException extends DomainException {

    public TooManyRequestsDomainException() {
    }

    public TooManyRequestsDomainException(String message) {
        super(message);
    }

    public TooManyRequestsDomainException(String domain, String reason, String details) {
        super(domain, reason, details);
    }

    public TooManyRequestsDomainException(Error error) {
        super(error);
    }

    public TooManyRequestsDomainException(List<Error> errors) {
        super(errors);
    }

    public TooManyRequestsDomainException(String message, List<Error> errors) {
        super(message, errors);
    }

    public TooManyRequestsDomainException(String message, Throwable cause, List<Error> errors) {
        super(message, cause, errors);
    }

    public TooManyRequestsDomainException(Throwable cause, List<Error> errors) {
        super(cause, errors);
    }

    public TooManyRequestsDomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}

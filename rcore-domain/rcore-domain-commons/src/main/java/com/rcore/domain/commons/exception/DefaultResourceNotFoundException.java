package com.rcore.domain.commons.exception;

public class DefaultResourceNotFoundException extends NotFoundDomainException {

    public final static String REASON = GlobalReason.NOT_FOUND;

    public DefaultResourceNotFoundException(String domain) {
        super(new Error(domain));
    }

    public static class Error extends DomainException.Error {
        public Error(String domain) {
            super(domain, REASON, "Resource not found by input values");
        }
    }
}

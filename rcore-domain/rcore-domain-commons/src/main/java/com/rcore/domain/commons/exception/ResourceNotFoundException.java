package com.rcore.domain.commons.exception;

public class ResourceNotFoundException extends NotFoundDomainException {

    public ResourceNotFoundException(String domain) {
        super(domain, GlobalReason.NOT_FOUND, "Resource not found by input values");
    }
}

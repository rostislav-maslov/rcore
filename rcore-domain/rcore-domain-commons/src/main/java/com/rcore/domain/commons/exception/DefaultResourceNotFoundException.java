package com.rcore.domain.commons.exception;

import java.util.List;

public class DefaultResourceNotFoundException extends NotFoundDomainException {

    public DefaultResourceNotFoundException(String domain) {
        super(domain, GlobalReason.NOT_FOUND, "Resource not found by input values");
    }
}

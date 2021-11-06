package com.rcore.domain.commons.exception;

import lombok.Getter;

import static com.rcore.commons.utils.StringUtils.camelCaseToUnderscores;

public class DefaultValueIsRequiredException extends BadRequestDomainException {

    public static final String REASON_POSTFIX = GlobalReason.IS_REQUIRED_POSTFIX;
    @Getter
    private final String invalidFieldName;

    public DefaultValueIsRequiredException(String domain, String invalidFieldName) {
        super(new Error(domain, invalidFieldName));
        this.invalidFieldName = invalidFieldName;
    }

    public static class Error extends DomainException.Error {
        public Error(String domain, String invalidFieldName) {
            super(domain, camelCaseToUnderscores(invalidFieldName) + REASON_POSTFIX, "Field " + invalidFieldName + " is required", invalidFieldName);
        }
    }

}

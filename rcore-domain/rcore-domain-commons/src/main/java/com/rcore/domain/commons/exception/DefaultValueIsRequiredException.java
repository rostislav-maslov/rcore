package com.rcore.domain.commons.exception;

import lombok.Getter;

import static com.rcore.commons.utils.StringUtils.camelCaseToUnderscores;

public class DefaultValueIsRequiredException extends BadRequestDomainException {

    @Getter
    private final String invalidFieldName;

    public DefaultValueIsRequiredException(String domain, String invalidFieldName) {
        super(domain, camelCaseToUnderscores(invalidFieldName) + GlobalReason.IS_REQUIRED_POSTFIX, "Field " + invalidFieldName + " is required");
        this.invalidFieldName = invalidFieldName;
    }

}

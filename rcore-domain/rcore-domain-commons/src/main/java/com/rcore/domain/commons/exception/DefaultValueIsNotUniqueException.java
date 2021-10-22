package com.rcore.domain.commons.exception;

import lombok.Getter;

import static com.rcore.commons.utils.StringUtils.camelCaseToUnderscores;

public class DefaultValueIsNotUniqueException extends BadRequestDomainException {

    @Getter
    private final String invalidFieldName;

    public DefaultValueIsNotUniqueException(String domain, String invalidFieldName) {
        super(domain, camelCaseToUnderscores(invalidFieldName) + GlobalReason.IS_NOT_UNIQUE_POSTFIX, "Value in field " + invalidFieldName + " is not unique. " + domain + " support only unique value");
        this.invalidFieldName = invalidFieldName;
    }

}

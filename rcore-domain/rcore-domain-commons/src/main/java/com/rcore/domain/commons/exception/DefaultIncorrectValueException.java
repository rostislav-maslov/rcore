package com.rcore.domain.commons.exception;

import lombok.Getter;

import static com.rcore.commons.utils.StringUtils.camelCaseToUnderscores;

public class DefaultIncorrectValueException extends BadRequestDomainException {

    @Getter
    private final String invalidFieldName;

    public DefaultIncorrectValueException(String domain, String invalidFieldName) {
        super(domain, camelCaseToUnderscores(invalidFieldName) + GlobalReason.IS_INCORRECT_POSTFIX, "Incorrect value in field " + invalidFieldName);
        this.invalidFieldName = invalidFieldName;
    }

}

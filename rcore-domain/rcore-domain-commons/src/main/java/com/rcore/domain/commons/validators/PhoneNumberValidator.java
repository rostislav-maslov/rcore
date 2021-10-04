package com.rcore.domain.commons.validators;

import com.rcore.commons.utils.impl.PhoneNumberValidatorImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PhoneNumberValidator implements ConstraintValidator<ValidatePhoneNumber, com.rcore.commons.utils.PhoneNumberValidator.PhoneNumber> {

    private final com.rcore.commons.utils.PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidatorImpl();

    @Override
    public boolean isValid(com.rcore.commons.utils.PhoneNumberValidator.PhoneNumber value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        return phoneNumberValidator.phoneIsValid(value);
    }
}

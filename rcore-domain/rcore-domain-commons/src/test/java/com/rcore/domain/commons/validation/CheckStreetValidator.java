package com.rcore.domain.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CheckStreetValidator implements ConstraintValidator<CheckStreet, Address> {

    @Override
    public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {
        if (address.getCity() == null)
            return true;

        return address.getStreet() != null;
    }
}

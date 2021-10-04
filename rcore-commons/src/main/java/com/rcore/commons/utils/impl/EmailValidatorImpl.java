package com.rcore.commons.utils.impl;

import com.rcore.commons.utils.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorImpl implements EmailValidator {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean emailIsValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}

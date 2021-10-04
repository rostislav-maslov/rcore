package com.rcore.commons.utils.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.rcore.commons.utils.PhoneNumberValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneNumberValidatorImpl implements PhoneNumberValidator {

    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean phoneIsValid(PhoneNumber phone) {
        var result = false;

        try {
            phoneNumberUtil.parse(phone.getPhoneNumber(), phone.getIsoTwoLetterCountryCode());
            result = true;
        } catch (NumberParseException e) {
            log.error("NumberParseException was thrown: " + e.toString());
        }

        return result;
    }
}

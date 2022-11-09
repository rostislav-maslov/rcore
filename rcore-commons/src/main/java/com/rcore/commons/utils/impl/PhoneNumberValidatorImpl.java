package com.rcore.commons.utils.impl;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.rcore.commons.utils.PhoneNumberValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneNumberValidatorImpl implements PhoneNumberValidator {

    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean phoneIsValid(String phone, String countryCode) {
        var result = false;

        try {
            Phonenumber.PhoneNumber pn = phoneNumberUtil.parse(phone, countryCode.toUpperCase());
            result = phoneNumberUtil.isValidNumber(pn);
        } catch (Exception e) {
            log.error("NumberParseException was thrown: " + e.toString());
        }

        return result;
    }
}

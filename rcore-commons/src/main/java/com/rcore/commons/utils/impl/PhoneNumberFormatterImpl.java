package com.rcore.commons.utils.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.rcore.commons.utils.PhoneNumberFormatter;
import com.rcore.commons.utils.PhoneNumberValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneNumberFormatterImpl implements PhoneNumberFormatter {

    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public FormattedPhoneNumber formatPhone(PhoneNumberValidator.PhoneNumber phone) {
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phone.getPhoneNumber(), phone.getIsoTwoLetterCountryCode());
            return FormattedPhoneNumber.of(phoneNumber.getNationalNumber(), phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));
        } catch (NumberParseException e) {
            log.error("NumberParseException was thrown: " + e.toString());
            throw new PhoneNumberFormattingException();
        }
    }
}

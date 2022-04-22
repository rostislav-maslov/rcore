package com.rcore.commons.utils;

import com.rcore.commons.utils.impl.PhoneNumberFormatterImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberFormatterTest {

    private final PhoneNumberFormatter phoneNumberFormatter = new PhoneNumberFormatterImpl();

    @ParameterizedTest
    @ValueSource(strings = {"+79023334455", "+7 (902) 333 44 55", "+7-(902)-333-44-55", "89023334455"})
    void FormatRussianPhone_Successful_Test(String phone) {
        PhoneNumberFormatter.FormattedPhoneNumber formatted = phoneNumberFormatter.formatPhone(new PhoneNumberValidator.PhoneNumber(phone, "ru"));
    }

}

package com.rcore.commons.utils;

import com.rcore.commons.utils.PhoneNumberValidator;
import com.rcore.commons.utils.impl.PhoneNumberValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorTest {

    PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidatorImpl();

    @ParameterizedTest
    @ValueSource(strings = {"+79023334455", "+7 (902) 333 44 55", "+7-(902)-333-44-55", "89990000000"})
    void ValidateRussianPhone_Successful_Test(String phone) {
       var result = phoneNumberValidator.phoneIsValid(new PhoneNumberValidator.PhoneNumber(phone, "ru"));
       assertTrue(result);
    }

}

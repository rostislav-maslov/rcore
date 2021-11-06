package com.rcore.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface PhoneNumberValidator {

    boolean phoneIsValid(PhoneNumber phone);

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class PhoneNumber {
        String phoneNumber;
        String isoTwoLetterCountryCode;
    }

}

package com.rcore.commons.utils;

import lombok.Value;

public interface PhoneNumberFormatter {

    FormattedPhoneNumber formatPhone(String phone, String countryCode);

    class PhoneNumberFormattingException extends RuntimeException {}

    @Value(staticConstructor = "of")
    class FormattedPhoneNumber {
        Long number;
        Integer countryCode;
        String formatted;

        public Long getFullNumber() {
            return Long.parseLong(countryCode.toString() + number.toString());
        }

    }

}

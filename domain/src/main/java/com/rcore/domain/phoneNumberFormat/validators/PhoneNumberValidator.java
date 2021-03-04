package com.rcore.domain.phoneNumberFormat.validators;

import com.rcore.commons.utils.PhoneNumberUtils;

public class PhoneNumberValidator {
    public boolean validatePhone(Long phone, PhoneNumberUtils.PhoneNumberFormat format) {
        return validatePhone(phone.toString(), format);
    }

    public boolean validatePhone(String phone, PhoneNumberUtils.PhoneNumberFormat format) {
        String clearPhone = phone.replaceAll("[^0-9]", "");

        String clearCode = format.getCode().replace("+", "");
        if (!clearPhone.substring(0, clearCode.length()).equals(clearCode))
            return true;

        if (!Integer.valueOf(clearPhone.length()).equals(format.getDigitsQuantityWithCode()))
            return true;

        if (format.getAllowedFirstDigits().stream()
                .noneMatch(digits -> clearPhone
                        .substring(clearCode.length(), clearCode.length() + digits.toString().length())
                        .equals(digits.toString())))
            return true;

        return false;
    }
}

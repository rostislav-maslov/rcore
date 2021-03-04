package com.rcore.adapter.domain.phoneNumberFormat.mapper;

import com.rcore.adapter.domain.phoneNumberFormat.dto.PhoneNumberFormatDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.commons.utils.PhoneNumberUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PhoneNumberFormatMapper implements ExampleDataMapper<PhoneNumberUtils.PhoneNumberFormat, PhoneNumberFormatDTO> {

    @Override
    public PhoneNumberFormatDTO map(PhoneNumberUtils.PhoneNumberFormat phoneNumberFormat) {
        return PhoneNumberFormatDTO.builder()
                .code(phoneNumberFormat.getCode())
                .digitsQuantity(phoneNumberFormat.getDigitsQuantity())
                .digitsQuantityWithCode(phoneNumberFormat.getDigitsQuantityWithCode())
                .allowedFirstDigits(phoneNumberFormat.getAllowedFirstDigits())
                .build();
    }
}

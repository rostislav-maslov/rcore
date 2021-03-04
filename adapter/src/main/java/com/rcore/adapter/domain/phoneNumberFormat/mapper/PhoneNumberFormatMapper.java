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

    @Override
    public PhoneNumberUtils.PhoneNumberFormat inverseMap(PhoneNumberFormatDTO phoneNumberFormatDTO) {
        return PhoneNumberUtils.PhoneNumberFormat.builder()
                .code(phoneNumberFormatDTO.getCode())
                .digitsQuantity(phoneNumberFormatDTO.getDigitsQuantity())
                .digitsQuantityWithCode(phoneNumberFormatDTO.getDigitsQuantityWithCode())
                .allowedFirstDigits(phoneNumberFormatDTO.getAllowedFirstDigits())
                .build();
    }
}

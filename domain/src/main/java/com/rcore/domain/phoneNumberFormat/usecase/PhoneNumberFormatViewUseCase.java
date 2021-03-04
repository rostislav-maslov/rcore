package com.rcore.domain.phoneNumberFormat.usecase;

import com.rcore.commons.utils.PhoneNumberUtils;
import com.rcore.domain.phoneNumberFormat.port.PhoneNumberFormatRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PhoneNumberFormatViewUseCase {
    private final PhoneNumberFormatRepository phoneNumberFormatRepository;

    public Optional<PhoneNumberUtils.PhoneNumberFormat> findById(String id) {
        return phoneNumberFormatRepository.findById(id);
    }
}

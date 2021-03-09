package com.rcore.adapter.domain.phoneNumberFormat;

import com.rcore.commons.utils.PhoneNumberUtils;
import com.rcore.domain.phoneNumberFormat.config.PhoneNumberFormatConfig;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PhoneNumberFormatAllAdapter {
    private final PhoneNumberFormatConfig config;

    public Optional<PhoneNumberUtils.PhoneNumberFormat> findById(String id) {
        return config.all.phoneNumberFormatViewUseCase()
                .findById(id);
    }
}

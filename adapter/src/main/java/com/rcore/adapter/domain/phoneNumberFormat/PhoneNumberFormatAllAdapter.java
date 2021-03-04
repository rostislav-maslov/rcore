package com.rcore.adapter.domain.phoneNumberFormat;

import com.rcore.adapter.domain.phoneNumberFormat.dto.PhoneNumberFormatDTO;
import com.rcore.adapter.domain.phoneNumberFormat.mapper.PhoneNumberFormatMapper;
import com.rcore.domain.phoneNumberFormat.config.PhoneNumberFormatConfig;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PhoneNumberFormatAllAdapter {
    private PhoneNumberFormatMapper phoneNumberFormatMapper = new PhoneNumberFormatMapper();
    private final PhoneNumberFormatConfig config;

    public Optional<PhoneNumberFormatDTO> findById(String id) {
        return config.all.phoneNumberFormatViewUseCase()
                .findById(id)
                .map(phoneNumberFormatMapper::map);
    }
}

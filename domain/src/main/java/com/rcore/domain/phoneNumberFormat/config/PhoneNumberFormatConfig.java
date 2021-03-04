package com.rcore.domain.phoneNumberFormat.config;

import com.rcore.domain.phoneNumberFormat.port.PhoneNumberFormatRepository;
import com.rcore.domain.phoneNumberFormat.usecase.PhoneNumberFormatViewUseCase;
import lombok.RequiredArgsConstructor;

public class PhoneNumberFormatConfig {
    @RequiredArgsConstructor
    public static class All {
        private final PhoneNumberFormatRepository phoneNumberFormatRepository;

        public PhoneNumberFormatViewUseCase phoneNumberFormatViewUseCase() {
            return new PhoneNumberFormatViewUseCase(phoneNumberFormatRepository);
        }
    }

    public final All all;

    public PhoneNumberFormatConfig(PhoneNumberFormatRepository phoneNumberFormatRepository) {
        this.all = new All(phoneNumberFormatRepository);
    }
}

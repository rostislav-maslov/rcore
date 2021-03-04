package com.rcore.domain.phoneNumberFormat.port;

import com.rcore.commons.utils.PhoneNumberUtils;

import java.util.Optional;

public interface PhoneNumberFormatRepository {
    Optional<PhoneNumberUtils.PhoneNumberFormat> findById(String id);
}

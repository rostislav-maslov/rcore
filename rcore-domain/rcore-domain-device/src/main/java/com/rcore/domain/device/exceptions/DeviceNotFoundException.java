package com.rcore.domain.device.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class DeviceNotFoundException extends DomainException {

    public DeviceNotFoundException() {
        super("Device not found");
    }

    public DeviceNotFoundException(String id) {
        super("Device not found by ID: " + id);
    }
}

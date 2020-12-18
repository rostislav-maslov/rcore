package com.rcore.domain.device.config;

import com.rcore.domain.device.port.DeviceIdGenerator;
import com.rcore.domain.device.port.DeviceRepository;
import com.rcore.domain.device.usecases.CreateDeviceUseCase;
import com.rcore.domain.device.usecases.FindDeviceByIdUseCase;
import com.rcore.domain.device.usecases.FindDeviceWithFiltersUseCase;
import com.rcore.domain.device.usecases.UpdateDeviceUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeviceConfig {

    private final DeviceRepository deviceRepository;
    private final DeviceIdGenerator deviceIdGenerator;

    public CreateDeviceUseCase createDeviceUseCase() {
        return new CreateDeviceUseCase(deviceRepository, deviceIdGenerator);
    }

    public UpdateDeviceUseCase updateDeviceUseCase() {
        return new UpdateDeviceUseCase(deviceRepository);
    }

    public FindDeviceByIdUseCase findDeviceByIdUseCase() {
        return new FindDeviceByIdUseCase(deviceRepository);
    }

    public FindDeviceWithFiltersUseCase findDeviceWithFiltersUseCase() {
        return new FindDeviceWithFiltersUseCase(deviceRepository);
    }

}

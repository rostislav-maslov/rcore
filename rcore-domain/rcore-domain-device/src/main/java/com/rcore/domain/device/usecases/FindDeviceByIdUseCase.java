package com.rcore.domain.device.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.device.entity.DeviceEntity;
import com.rcore.domain.device.port.DeviceRepository;

public class FindDeviceByIdUseCase extends AbstractFindByIdUseCase<String, DeviceEntity, DeviceRepository> {

    public FindDeviceByIdUseCase(DeviceRepository repository) {
        super(repository);
    }
}

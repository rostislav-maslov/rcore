package com.rcore.domain.device.usecases;

import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.rcore.domain.device.entity.DeviceEntity;
import com.rcore.domain.device.port.DeviceRepository;
import com.rcore.domain.device.port.filters.DeviceFilters;

public class FindDeviceWithFiltersUseCase extends AbstractFindWithFiltersUseCase<DeviceEntity, DeviceFilters, DeviceRepository> {

    public FindDeviceWithFiltersUseCase(DeviceRepository repository) {
        super(repository);
    }
}

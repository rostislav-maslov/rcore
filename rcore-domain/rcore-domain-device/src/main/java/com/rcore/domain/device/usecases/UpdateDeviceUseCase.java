package com.rcore.domain.device.usecases;

import com.rcore.domain.commons.usecase.AbstractUpdateUseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.device.entity.DeviceEntity;
import com.rcore.domain.device.exceptions.DeviceNotFoundException;
import com.rcore.domain.device.port.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public class UpdateDeviceUseCase extends AbstractUpdateUseCase<DeviceEntity, DeviceRepository, UpdateDeviceUseCase.InputValues> {

    public UpdateDeviceUseCase(DeviceRepository repository) {
        super(repository);
    }

    @Override
    public SingletonEntityOutputValues<DeviceEntity> execute(InputValues inputValues) {
        DeviceEntity deviceEntity = repository.findById(inputValues.getId())
                .orElseThrow(() -> new DeviceNotFoundException(inputValues.getId()));
        deviceEntity.setToken(inputValues.getToken());
        deviceEntity.setCredentialId(inputValues.getCredentialId());
        deviceEntity.setInfo(inputValues.getInfo());
        deviceEntity.setBuildVersion(inputValues.getBuildVersion());
        deviceEntity.setOperatingSystemId(inputValues.getOperatingSystemId());
        deviceEntity.setType(inputValues.getType());
        deviceEntity.setData(inputValues.getData());
        return SingletonEntityOutputValues.of(repository.save(deviceEntity));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Data
    public static class InputValues extends CreateDeviceUseCase.InputValues {
        private String id;
    }

}

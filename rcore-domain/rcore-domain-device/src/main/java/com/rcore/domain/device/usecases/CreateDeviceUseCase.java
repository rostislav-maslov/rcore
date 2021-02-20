package com.rcore.domain.device.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.device.entity.DeviceEntity;
import com.rcore.domain.device.port.DeviceIdGenerator;
import com.rcore.domain.device.port.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

public class CreateDeviceUseCase extends AbstractCreateUseCase<DeviceEntity, DeviceIdGenerator, DeviceRepository, CreateDeviceUseCase.InputValues> {

    public CreateDeviceUseCase(DeviceRepository repository, DeviceIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<DeviceEntity> execute(InputValues inputValues) {
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(idGenerator.generate());
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
    public static class InputValues implements UseCase.InputValues {
        protected String token;
        protected String credentialId;
        protected String info;
        protected String buildVersion;
        protected String operatingSystemId;
        protected DeviceEntity.Type type;
        protected HashMap<String, Object> data;
    }

}

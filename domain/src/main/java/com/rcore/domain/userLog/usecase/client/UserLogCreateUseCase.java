package com.rcore.domain.userLog.usecase.client;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userLog.entity.UserLogEntity;
import com.rcore.domain.userLog.port.UserLogIdGenerator;
import com.rcore.domain.userLog.port.UserLogRepository;

import java.util.Optional;

public class UserLogCreateUseCase extends UserLogBaseUseCase {

    private UserLogIdGenerator userLogIdGenerator;

    public UserLogCreateUseCase(UserLogRepository userLogRepository, UserLogIdGenerator userLogIdGenerator) {
        super(userLogRepository);
        this.userLogIdGenerator = userLogIdGenerator;
    }

    public Optional<UserLogEntity> create(UserLogEntity userLogEntity) {
        userLogEntity.setId(userLogIdGenerator.generate());
        return userLogRepository.save(userLogEntity);
    }

    public UserLogEntity success(UserEntity userEntity, UserLogEntity.DeviceData deviceData) {
        UserLogEntity userLogEntity = new UserLogEntity();

        userLogEntity.setUserId(userEntity.getId());
        userLogEntity.setStatus(UserLogEntity.Status.SUCCESS);
        userLogEntity.setDeviceData(deviceData);

        throw new RuntimeException();
    }

    public UserLogEntity fail(UserEntity userEntity, UserLogEntity.DeviceData deviceData) {
        UserLogEntity userLogEntity = new UserLogEntity();

        userLogEntity.setUserId(userEntity.getId());
        userLogEntity.setStatus(UserLogEntity.Status.FAIL);
        userLogEntity.setDeviceData(deviceData);

        throw new RuntimeException();
    }

}

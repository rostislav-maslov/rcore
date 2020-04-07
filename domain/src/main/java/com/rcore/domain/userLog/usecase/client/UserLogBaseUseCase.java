package com.rcore.domain.userLog.usecase.client;

import com.rcore.domain.userLog.entity.UserLogEntity;
import com.rcore.domain.userLog.port.UserLogRepository;

public class UserLogBaseUseCase {
    protected UserLogRepository<UserLogEntity> userLogRepository;

    public UserLogBaseUseCase(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }
}

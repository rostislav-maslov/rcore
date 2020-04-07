package com.rcore.domain.userLog.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.userLog.entity.UserLogEntity;

public interface UserLogRepository<Entity extends UserLogEntity> extends CRUDRepository<String, Entity> {

}

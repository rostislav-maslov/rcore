package com.rcore.domain.commons.port;

import com.rcore.domain.commons.entity.BaseEntity;

public interface CreateUpdateRepository<Entity extends BaseEntity> {
    Entity save(Entity entity);
}

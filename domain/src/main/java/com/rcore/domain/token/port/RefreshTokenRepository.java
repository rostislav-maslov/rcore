package com.rcore.domain.token.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.token.entity.RefreshTokenEntity;

import java.util.List;

public abstract class RefreshTokenRepository<Entity extends RefreshTokenEntity> extends CRUDRepository<String, Entity> {

    public abstract void expireRefreshToken(Entity refreshTokenEntity);
    public abstract List<Entity> findAllActiveByUserId(String userId);
}

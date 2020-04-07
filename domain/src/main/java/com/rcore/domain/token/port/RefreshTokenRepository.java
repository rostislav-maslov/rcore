package com.rcore.domain.token.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.token.entity.RefreshTokenEntity;

import java.util.List;

public interface RefreshTokenRepository<Entity extends RefreshTokenEntity> extends CRUDRepository<String, Entity> {

    void expireRefreshToken(Entity refreshTokenEntity);
    List<Entity> findAllActiveByUserId(String userId);
}

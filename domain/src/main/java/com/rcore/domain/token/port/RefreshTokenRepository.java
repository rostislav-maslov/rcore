package com.rcore.domain.token.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.token.entity.RefreshTokenEntity;

import java.util.List;

public interface RefreshTokenRepository extends CRUDRepository<String, RefreshTokenEntity> {

    void expireRefreshToken(RefreshTokenEntity refreshTokenEntity);
    List<RefreshTokenEntity> findAllActiveByUserId(String userId);
}

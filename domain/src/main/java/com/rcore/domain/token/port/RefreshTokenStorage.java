package com.rcore.domain.token.port;

import com.rcore.domain.token.entity.RefreshTokenEntity;

import java.util.Optional;

public interface RefreshTokenStorage {

    /**
     * Сохраняем refreshToken в storage
     * @param refreshTokenEntity
     * @return
     */
    void put(RefreshTokenEntity refreshTokenEntity);

    /**
     * Поиск токена в storage
     * @param id
     * @return
     */
    Optional<RefreshTokenEntity> findById(String id);
}

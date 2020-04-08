package com.rcore.domain.token.port;

import com.rcore.domain.token.entity.AccessTokenEntity;

import java.util.Optional;

public interface AccessTokenStorage {
    /**
     * Получаем текущий AccessToken из storage
     * @return
     */
    Optional<AccessTokenEntity> current();

    /**
     * Сохраняем accessToken в сессию/storage
     * @param accessTokenEntity
     * @return
     */
    void put(AccessTokenEntity accessTokenEntity);

}

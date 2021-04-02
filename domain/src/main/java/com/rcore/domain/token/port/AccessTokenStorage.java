package com.rcore.domain.token.port;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.filters.AccessTokenFilters;

import java.util.List;
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

    /**
     * Поиск токена в storage
     * @param id
     * @return
     */
    Optional<AccessTokenEntity> findById(String id);

    Optional<AccessTokenEntity> findTokenById(String id);

    String findJWTById(String id);

    Optional<AccessTokenEntity> findTokenByJWT(String jwtToken);

    SearchResult<AccessTokenEntity> findWithFilters(AccessTokenFilters filters);

    void expireAccessToken(AccessTokenEntity accessTokenEntity);

    void expireAllAccessTokenByRefreshTokenId(String refreshTokenId);

    void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId);

}

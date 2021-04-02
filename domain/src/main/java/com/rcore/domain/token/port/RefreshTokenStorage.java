package com.rcore.domain.token.port;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.filters.RefreshTokenFilters;

import java.util.List;
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

    /**
     *
     * @param refreshTokenEntity
     */
    void expireRefreshToken(RefreshTokenEntity refreshTokenEntity);

    /**
     *
     * @param userId
     * @return
     */
    List<RefreshTokenEntity> findAllActiveByUserId(String userId);

    SearchResult<RefreshTokenEntity> findWithFilters(RefreshTokenFilters filters);

    Optional<RefreshTokenEntity> findTokenById(String id);

    Optional<RefreshTokenEntity> findTokenByJWT(String jwtToken);

    String findJWTById(String id);
}

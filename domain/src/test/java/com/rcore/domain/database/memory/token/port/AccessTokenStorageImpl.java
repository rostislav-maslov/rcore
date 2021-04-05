package com.rcore.domain.database.memory.token.port;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.filters.AccessTokenFilters;

import java.util.Optional;

public class AccessTokenStorageImpl implements AccessTokenStorage {

    @Override
    public Optional<AccessTokenEntity> current() {
        return Optional.ofNullable(AccessTokenEntity.builder()
                .build());
    }

    @Override
    public void put(AccessTokenEntity accessTokenEntity) {

    }

    @Override
    public Optional<AccessTokenEntity> findById(String id) {
        return null;
    }

    @Override
    public void expireAccessToken(AccessTokenEntity accessTokenEntity) {

    }

    @Override
    public void assignedRefreshedStatusToAccessToken(AccessTokenEntity accessTokenEntity) {

    }

    @Override
    public void expireAllAccessTokenByRefreshTokenId(String refreshTokenId) {

    }

    @Override
    public void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId) {

    }

    @Override
    public SearchResult<AccessTokenEntity> findWithFilters(AccessTokenFilters filters) {
        return null;
    }

    @Override
    public Optional<AccessTokenEntity> findTokenById(String id) {
        return Optional.empty();
    }

    @Override
    public String findJWTById(String id) {
        return null;
    }

    @Override
    public Optional<AccessTokenEntity> findTokenByJWT(String jwtToken) {
        return Optional.empty();
    }
}

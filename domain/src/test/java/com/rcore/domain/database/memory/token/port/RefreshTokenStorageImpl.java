package com.rcore.domain.database.memory.token.port;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.port.filters.RefreshTokenFilters;

import java.util.List;
import java.util.Optional;

public class RefreshTokenStorageImpl implements RefreshTokenStorage {

    @Override
    public void put(RefreshTokenEntity refreshTokenEntity) {

    }

    @Override
    public Optional<RefreshTokenEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void expireRefreshToken(RefreshTokenEntity refreshTokenEntity) {

    }

    @Override
    public List<RefreshTokenEntity> findAllActiveByUserId(String userId) {
        return null;
    }

    @Override
    public SearchResult<RefreshTokenEntity> findWithFilters(RefreshTokenFilters filters) {
        return null;
    }

    @Override
    public Optional<RefreshTokenEntity> findTokenById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<RefreshTokenEntity> findTokenByJWT(String jwtToken) {
        return Optional.empty();
    }

    @Override
    public String findJWTById(String id) {
        return null;
    }
}

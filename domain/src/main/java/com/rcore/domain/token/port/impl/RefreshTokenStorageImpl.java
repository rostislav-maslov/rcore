package com.rcore.domain.token.port.impl;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.port.filters.RefreshTokenFilters;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RefreshTokenStorageImpl implements RefreshTokenStorage {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void put(RefreshTokenEntity refreshTokenEntity) {
        refreshTokenEntity = refreshTokenRepository.save(refreshTokenEntity);
    }

    @Override
    public Optional<RefreshTokenEntity> findById(String id) {
            return refreshTokenRepository.findById(id);
    }

    @Override
    public void expireRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        refreshTokenRepository.expireRefreshToken(refreshTokenEntity);
    }

    @Override
    public List<RefreshTokenEntity> findAllActiveByUserId(String userId) {
        return refreshTokenRepository.findAllActiveByUserId(userId);
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

    @Override
    public void deactivateRefreshToken(RefreshTokenEntity refreshTokenEntity) {

    }
}

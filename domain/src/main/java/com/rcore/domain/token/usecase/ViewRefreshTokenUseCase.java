package com.rcore.domain.token.usecase;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.port.filters.RefreshTokenFilters;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ViewRefreshTokenUseCase {
    private final RefreshTokenStorage refreshTokenStorage;

    public SearchResult<RefreshTokenEntity> findWithFilters(RefreshTokenFilters filters) {
        return refreshTokenStorage.findWithFilters(filters);
    }

    public Optional<RefreshTokenEntity> findTokenById(String id) {
        return refreshTokenStorage.findTokenById(id);
    }

    public Optional<RefreshTokenEntity> findTokenByJWT(String jwtToken) {
        return refreshTokenStorage.findTokenByJWT(jwtToken);
    }

    public String findJWTById(String id) {
        return refreshTokenStorage.findJWTById(id);
    }
}

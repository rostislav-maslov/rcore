package com.rcore.restapi.security.entities.token.port;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.port.filters.RefreshTokenFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RefreshTokenStorageImpl implements RefreshTokenStorage {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void put(RefreshTokenEntity refreshTokenEntity) {
        refreshTokenRepository.save(refreshTokenEntity);
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
    public void deactivateRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        refreshTokenRepository.deactivateRefreshToken(refreshTokenEntity);
    }

    @Override
    public List<RefreshTokenEntity> findAllActiveByUserId(String userId) {
        return refreshTokenRepository.findAllActiveByUserId(userId);
    }

    @Override
    public SearchResult<RefreshTokenEntity> findWithFilters(RefreshTokenFilters filters) {
        return refreshTokenRepository.findWithFilters(filters);
    }

    @Override
    public Optional<RefreshTokenEntity> findTokenById(String id) {
        return refreshTokenRepository.findById(id);
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

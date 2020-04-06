package com.rcore.domain.token.usecase;

import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.user.entity.UserEntity;

import java.util.List;

public class ExpireTokenUseCase {

    private final RefreshTokenRepository repository;

    public ExpireTokenUseCase(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    public void logout(UserEntity userEntity) {
        List<RefreshTokenEntity> refreshTokenEntities = repository.findAllActiveByUserId(userEntity.getId());
        for (RefreshTokenEntity refreshTokenEntity : refreshTokenEntities) {
            repository.expireRefreshToken(refreshTokenEntity);
        }
    }

    public void logout(AccessTokenEntity accessTokenEntity) {
        repository.findById(accessTokenEntity.getCreateFromRefreshTokenId())
                .map(refreshToken -> {
                    logout(refreshToken);
                    return refreshToken;
                });
    }

    public void logout(RefreshTokenEntity refreshTokenEntity) {
        repository.expireRefreshToken(refreshTokenEntity);

        if (refreshTokenEntity.getCreateFromType().equals(RefreshTokenEntity.CreateFrom.REFRESH)) {
            repository.findById(refreshTokenEntity.getCreateFromTokenId()).map(refreshToken -> {
                logout(refreshToken);
                return refreshToken;
            });
        }
    }

}

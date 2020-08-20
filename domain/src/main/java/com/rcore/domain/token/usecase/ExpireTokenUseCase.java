package com.rcore.domain.token.usecase;

import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ExpireTokenUseCase {

    private final RefreshTokenStorage refreshTokenStorage;
    private final AccessTokenStorage accessTokenStorage;

    public void logout(UserEntity userEntity) {
        List<RefreshTokenEntity> refreshTokenEntities = refreshTokenStorage.findAllActiveByUserId(userEntity.getId());
        for (RefreshTokenEntity refreshTokenEntity : refreshTokenEntities) {
            refreshTokenStorage.expireRefreshToken(refreshTokenEntity);
        }
    }

    public void logout(AccessTokenEntity accessTokenEntity) {
        refreshTokenStorage.findById(accessTokenEntity.getCreateFromRefreshTokenId())
                .map(refreshToken -> {
                    logout(refreshToken);
                    accessTokenStorage.deactivateAllAccessTokenByRefreshTokenId(refreshToken.getId());
                    return refreshToken;
                });
    }

    public void logout(RefreshTokenEntity refreshTokenEntity) {
        refreshTokenStorage.expireRefreshToken(refreshTokenEntity);

        if (refreshTokenEntity.getCreateFromType().equals(RefreshTokenEntity.CreateFrom.REFRESH)) {
            refreshTokenStorage.findById(refreshTokenEntity.getCreateFromTokenId())
                    .map(refreshToken -> {
                        logout(refreshToken);
                        return refreshToken;
                    });
        }
    }

}

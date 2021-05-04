package com.rcore.domain.token.usecase;

import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenIsExpiredException;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.exception.BlockedUserTriesToLogoutException;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ExpireTokenUseCase {

    private final UserRepository userRepository;
    private final RefreshTokenStorage refreshTokenStorage;
    private final AccessTokenStorage accessTokenStorage;

    public void logout(UserEntity userEntity) {
        List<RefreshTokenEntity> refreshTokenEntities = refreshTokenStorage.findAllActiveByUserId(userEntity.getId());
        for (RefreshTokenEntity refreshTokenEntity : refreshTokenEntities) {
            refreshTokenStorage.expireRefreshToken(refreshTokenEntity);
        }
    }

    public void logout(AccessTokenEntity accessTokenEntity) throws AuthenticationException, UserNotFoundException, UserBlockedException, TokenExpiredException, BlockedUserTriesToLogoutException {
        //Ищем юзера по переданному токену
        UserEntity userEntity = userRepository.findById(accessTokenEntity.getUserId())
                .orElseThrow(UserNotFoundException::new);
        //Ищем переданный аксесс в бд
        AccessTokenEntity accessToken = accessTokenStorage.findById(accessTokenEntity.getId())
                .orElseThrow(AuthenticationException::new);
        //Проверяем статус юзера
        if (!userEntity.getStatus().equals(UserStatus.ACTIVE))
            throw new BlockedUserTriesToLogoutException();

        if (!accessToken.getStatus().equals(RefreshTokenEntity.Status.ACTIVE) || !accessToken.isActive()) {
            accessTokenStorage.expireAccessToken(accessTokenEntity);
            throw new TokenExpiredException();
        }

        refreshTokenStorage.findById(accessToken.getCreateFromRefreshTokenId())
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

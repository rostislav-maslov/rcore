package com.rcore.domain.token.usecase;

import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.exception.RefreshTokenCreationException;
import com.rcore.domain.token.port.AccessTokenIdGenerator;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Date;
import java.util.Optional;

public class CreateAccessTokenUseCase {
    private final AccessTokenIdGenerator idGenerator;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;

    public CreateAccessTokenUseCase(AccessTokenIdGenerator idGenerator, CreateRefreshTokenUseCase createRefreshTokenUseCase) {
        this.idGenerator = idGenerator;
        this.createRefreshTokenUseCase = createRefreshTokenUseCase;
    }

    public AccessTokenEntity create(UserEntity userEntity) throws RefreshTokenCreationException {
        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.create(userEntity);

        return create(userEntity, refreshTokenEntity);
    }

    public AccessTokenEntity create(UserEntity userEntity, RefreshTokenEntity refreshTokenEntity) {
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();

        accessTokenEntity.setId(idGenerator.generate());
        accessTokenEntity.setUserId(refreshTokenEntity.getUserId());
        accessTokenEntity.setRoles(userEntity.getRoles());
        accessTokenEntity.setExpireAt(new Date(new Date().getTime() + refreshTokenEntity.getExpireTimeAccessToken()));
        accessTokenEntity.setCreateFromRefreshTokenId(refreshTokenEntity.getId());

        accessTokenEntity.setSign(AccessTokenEntity.sign(accessTokenEntity.getId(), accessTokenEntity.getExpireAt().getTime(), refreshTokenEntity));

        return accessTokenEntity;
    }
}

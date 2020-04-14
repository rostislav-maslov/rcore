package com.rcore.domain.token.usecase;

import com.rcore.commons.utils.DateTimeUtils;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.exception.RefreshTokenCreationException;
import com.rcore.domain.token.port.AccessTokenIdGenerator;
import com.rcore.domain.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Date;

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
        accessTokenEntity.setExpireAt(DateTimeUtils.fromMillis(DateTimeUtils.getNowMillis() + refreshTokenEntity.getExpireTimeAccessToken()));
        accessTokenEntity.setCreateFromRefreshTokenId(refreshTokenEntity.getId());

        accessTokenEntity.setSign(AccessTokenEntity.sign(accessTokenEntity.getId(), DateTimeUtils.getMillis(accessTokenEntity.getExpireAt()), refreshTokenEntity));

        return accessTokenEntity;
    }
}

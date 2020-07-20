package com.rcore.domain.token.usecase;

import com.rcore.commons.utils.DateTimeUtils;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenIdGenerator;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.port.TokenSaltGenerator;
import com.rcore.domain.user.entity.UserEntity;

public class CreateRefreshTokenUseCase {

    private final RefreshTokenIdGenerator idGenerator;
    private final RefreshTokenStorage refreshTokenStorage;
    private final TokenSaltGenerator tokenSaltGenerator;


    public CreateRefreshTokenUseCase(RefreshTokenIdGenerator idGenerator, RefreshTokenStorage refreshTokenStorage, TokenSaltGenerator tokenSaltGenerator) {
        this.idGenerator = idGenerator;
        this.refreshTokenStorage = refreshTokenStorage;
        this.tokenSaltGenerator = tokenSaltGenerator;
    }

    private RefreshTokenEntity create(String userId, RefreshTokenEntity.CreateFrom createFrom, String refreshTokenId){
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();

        refreshTokenEntity.setId(this.idGenerator.generate());
        refreshTokenEntity.setUserId(userId);
        refreshTokenEntity.setExpireAt(DateTimeUtils.fromMillis(DateTimeUtils.getNowMillis() + refreshTokenEntity.getExpireTimeRefreshToken()));
        refreshTokenEntity.setStatus(RefreshTokenEntity.Status.ACTIVE);

        refreshTokenEntity.setCreateFromTokenId(refreshTokenId);
        refreshTokenEntity.setCreateFromType(createFrom);

        refreshTokenEntity.setSalt(tokenSaltGenerator.generate());
        return refreshTokenEntity;
    }

    public RefreshTokenEntity create(UserEntity userEntity){
        RefreshTokenEntity refreshTokenEntity = create(userEntity.getId(), RefreshTokenEntity.CreateFrom.LOGIN, null);
        refreshTokenStorage.put(refreshTokenEntity);
        return refreshTokenEntity;
    }

    public RefreshTokenEntity create(RefreshTokenEntity oldRefreshTokenEntity){
        RefreshTokenEntity refreshTokenEntity = create(oldRefreshTokenEntity.getUserId(), RefreshTokenEntity.CreateFrom.LOGIN, oldRefreshTokenEntity.getId());
        refreshTokenStorage.put(refreshTokenEntity);
        return refreshTokenEntity;
    }

}

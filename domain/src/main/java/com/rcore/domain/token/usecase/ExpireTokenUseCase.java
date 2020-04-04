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

    public void logout(UserEntity userEntity){
        List<RefreshTokenEntity> refreshTokenEntities = repository.findAllActiveByUserId(userEntity.getId());
        for(RefreshTokenEntity refreshTokenEntity : refreshTokenEntities){
            repository.expireRefreshToken(refreshTokenEntity);
        }
    }

    public void logout(AccessTokenEntity accessTokenEntity){
        RefreshTokenEntity refreshTokenEntity = repository.findById(accessTokenEntity.getCreateFromRefreshTokenId());
        logout(refreshTokenEntity);
    }

    public void logout(RefreshTokenEntity refreshTokenEntity){
        repository.expireRefreshToken(refreshTokenEntity);

        if(refreshTokenEntity.getCreateFromType().equals(RefreshTokenEntity.CreateFrom.REFRESH)){
            RefreshTokenEntity parent = repository.findById(refreshTokenEntity.getCreateFromTokenId());
            if(parent != null) logout(parent);
        }
    }

}

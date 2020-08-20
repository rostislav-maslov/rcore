package com.rcore.domain.database.memory.token.port;

import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.AccessTokenStorage;

import java.util.Optional;

public class AccessTokenStorageImpl implements AccessTokenStorage {



    @Override
    public Optional<AccessTokenEntity> current() {
        return Optional.ofNullable(AccessTokenEntity.builder()
                .build());
    }

    @Override
    public void put(AccessTokenEntity accessTokenEntity) {

    }

    @Override
    public Optional<AccessTokenEntity> findById(String id) {
        return null;
    }

    @Override
    public void expireAccessToken(AccessTokenEntity accessTokenEntity) {

    }

    @Override
    public void expireAllAccessTokenByRefreshTokenId(String refreshTokenId) {

    }

    @Override
    public void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId) {

    }
}

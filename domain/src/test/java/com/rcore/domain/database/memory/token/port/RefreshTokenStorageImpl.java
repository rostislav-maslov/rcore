package com.rcore.domain.database.memory.token.port;

import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenStorage;

import java.util.Optional;

public class RefreshTokenStorageImpl implements RefreshTokenStorage {

    @Override
    public void put(RefreshTokenEntity refreshTokenEntity) {

    }

    @Override
    public Optional<RefreshTokenEntity> findById(String id) {
        return Optional.empty();
    }
}

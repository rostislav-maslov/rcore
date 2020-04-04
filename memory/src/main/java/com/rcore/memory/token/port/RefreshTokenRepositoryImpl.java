package com.rcore.memory.token.port;

import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.memory.base.port.CRUDRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class RefreshTokenRepositoryImpl extends CRUDRepositoryImpl<String, RefreshTokenEntity> implements RefreshTokenRepository {
    @Override
    public RefreshTokenEntity save(RefreshTokenEntity object) {
        container.put(object.getId(), object);
        return object;
    }

    @Override
    public Boolean delete(RefreshTokenEntity object) {
         container.remove(object.getId());
         return true;
    }

    @Override
    public Boolean deleteById(String id) {
        container.remove(id);
        return true;
    }

    @Override
    public RefreshTokenEntity findById(String id) {
        return container.get(id);
    }

    @Override
    public void expireRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        for (RefreshTokenEntity entity : container.values()) {
            if (entity.getId().equals(refreshTokenEntity.getId())) {

                entity.setStatus(RefreshTokenEntity.Status.INACTIVE);

            }
        }
    }

    @Override
    public List<RefreshTokenEntity> findAllActiveByUserId(String userId) {
        List<RefreshTokenEntity> result = new ArrayList<>();
        for (RefreshTokenEntity refreshTokenEntity : container.values()) {
            if (refreshTokenEntity.getUserId().equals(userId) && refreshTokenEntity.isActive() == true) {
                result.add(refreshTokenEntity);
            }
        }
        return result;
    }
}

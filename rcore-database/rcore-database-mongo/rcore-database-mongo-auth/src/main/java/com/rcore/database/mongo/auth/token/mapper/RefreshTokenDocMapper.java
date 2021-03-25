package com.rcore.database.mongo.auth.token.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.database.mongo.auth.token.model.RefreshTokenDoc;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.RefreshTokenIdGenerator;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefreshTokenDocMapper implements ExampleDataMapper<RefreshTokenEntity, RefreshTokenDoc> {

    private final RefreshTokenIdGenerator<ObjectId> idGenerator;

    @Override
    public RefreshTokenDoc map(RefreshTokenEntity refreshTokenEntity) {
        return RefreshTokenDoc.builder()
                .id(idGenerator.parse(refreshTokenEntity.getId()))
                .salt(refreshTokenEntity.getSalt())
                .status(refreshTokenEntity.getStatus())
                .updatedAt(refreshTokenEntity.getUpdatedAt())
                .createdAt(refreshTokenEntity.getCreatedAt())
                .createFromTokenId(refreshTokenEntity.getCreateFromTokenId())
                .createFromType(refreshTokenEntity.getCreateFromType())
                .credential(refreshTokenEntity.getCredential())
                .expireAt(refreshTokenEntity.getExpireAt())
                .expireTimeAccessToken(refreshTokenEntity.getExpireTimeAccessToken())
                .expireTimeRefreshToken(refreshTokenEntity.getExpireTimeRefreshToken())
                .build();
    }

    @Override
    public RefreshTokenEntity inverseMap(RefreshTokenDoc refreshTokenDoc) {
        RefreshTokenEntity entity = new RefreshTokenEntity();
        entity.setId(refreshTokenDoc.getId().toString());
        entity.setSalt(refreshTokenDoc.getSalt());
        entity.setStatus(refreshTokenDoc.getStatus());
        entity.setUpdatedAt(refreshTokenDoc.getUpdatedAt());
        entity.setCreatedAt(refreshTokenDoc.getCreatedAt());
        entity.setCreateFromTokenId(refreshTokenDoc.getCreateFromTokenId());
        entity.setCreateFromType(refreshTokenDoc.getCreateFromType());
        entity.setCredential(refreshTokenDoc.getCredential());
        entity.setExpireAt(refreshTokenDoc.getExpireAt());
        entity.setExpireTimeAccessToken(refreshTokenDoc.getExpireTimeAccessToken());
        entity.setExpireTimeRefreshToken(refreshTokenDoc.getExpireTimeRefreshToken());
        return entity;
    }
}

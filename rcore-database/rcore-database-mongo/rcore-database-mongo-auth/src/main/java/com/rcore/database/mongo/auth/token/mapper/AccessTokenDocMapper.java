package com.rcore.database.mongo.auth.token.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.database.mongo.auth.token.model.AccessTokenDoc;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenIdGenerator;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccessTokenDocMapper implements ExampleDataMapper<AccessTokenEntity, AccessTokenDoc> {

    private final AccessTokenIdGenerator<ObjectId> idGenerator;

    @Override
    public AccessTokenDoc map(AccessTokenEntity accessTokenEntity) {
        return AccessTokenDoc.builder()
                .createByRefreshTokenId(accessTokenEntity.getCreateByRefreshTokenId())
                .createdAt(accessTokenEntity.getCreatedAt())
                .credential(accessTokenEntity.getCredential())
                .expireAt(accessTokenEntity.getExpireAt())
                .id(idGenerator.parse(accessTokenEntity.getId()))
                .sign(accessTokenEntity.getSign())
                .status(accessTokenEntity.getStatus())
                .updatedAt(accessTokenEntity.getUpdatedAt())
                .build();
    }

    @Override
    public AccessTokenEntity inverseMap(AccessTokenDoc accessTokenDoc) {
        AccessTokenEntity entity = new AccessTokenEntity();
        entity.setId(accessTokenDoc.getId().toString());
        entity.setSign(accessTokenDoc.getSign());
        entity.setStatus(accessTokenDoc.getStatus());
        entity.setCreateByRefreshTokenId(accessTokenDoc.getCreateByRefreshTokenId());
        entity.setCredential(accessTokenDoc.getCredential());
        entity.setExpireAt(accessTokenDoc.getExpireAt());
        entity.setCreatedAt(accessTokenDoc.getCreatedAt());
        entity.setUpdatedAt(accessTokenDoc.getUpdatedAt());
        return entity;
    }
}


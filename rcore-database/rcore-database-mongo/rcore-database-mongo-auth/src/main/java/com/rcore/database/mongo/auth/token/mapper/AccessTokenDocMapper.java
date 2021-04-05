package com.rcore.database.mongo.auth.token.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.database.mongo.auth.credential.mapper.CredentialDocMapper;
import com.rcore.database.mongo.auth.token.model.AccessTokenDoc;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenIdGenerator;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccessTokenDocMapper implements ExampleDataMapper<AccessTokenEntity, AccessTokenDoc> {

    private final AccessTokenIdGenerator<ObjectId> idGenerator;
    private final CredentialDocMapper credentialDocMapper;

    @Override
    public AccessTokenDoc map(AccessTokenEntity accessTokenEntity) {
        return AccessTokenDoc.builder()
                .createByRefreshTokenId(accessTokenEntity.getCreateByRefreshTokenId())
                .createdAt(accessTokenEntity.getCreatedAt())
                .credential(Optional.ofNullable(accessTokenEntity.getCredential())
                        .map(credentialDocMapper::map)
                        .orElse(null))
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
        entity.setCredential(Optional.ofNullable(accessTokenDoc.getCredential())
                .map(credentialDocMapper::inverseMap)
                .orElse(null));
        entity.setExpireAt(accessTokenDoc.getExpireAt());
        entity.setCreatedAt(accessTokenDoc.getCreatedAt());
        entity.setUpdatedAt(accessTokenDoc.getUpdatedAt());
        return entity;
    }
}


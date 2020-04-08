package com.rcore.database.mongo.token.port.model;

import com.rcore.domain.token.entity.RefreshTokenEntity;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("refreshTokenEntity")
@CompoundIndexes({
        @CompoundIndex(def = "{'_id': 1}", name = "_id_")
})
@SuperBuilder
public class RefreshTokenDoc extends RefreshTokenEntity {

    public RefreshTokenEntity toEntity(){
        return this;
    }

    public static RefreshTokenDoc toDoc(RefreshTokenEntity entity) {
        return RefreshTokenDoc.builder()
                .id(entity.getId())
                .createFromTokenId(entity.getCreateFromTokenId())
                .createFromType(entity.getCreateFromType())
                .expireAt(entity.getExpireAt())
                .expireTimeAccessToken(entity.getExpireTimeAccessToken())
                .expireTimeRefreshToken(entity.getExpireTimeRefreshToken())
                .salt(entity.getSalt())
                .status(entity.getStatus())
                .userId(entity.getUserId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .timeZone(entity.getTimeZone())
                .build();
    }

}

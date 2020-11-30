package com.rcore.database.mongo.domain.userPasswordRecover.model;

import com.rcore.database.mongo.domain.user.model.UserDoc;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Document
@CompoundIndexes({
        @CompoundIndex(def = "{'_id': 1}", name = "_id_")
})
@SuperBuilder
@Getter
@NoArgsConstructor
public class UserPasswordRecoverDoc extends UserPasswordRecoverEntity {

    public UserPasswordRecoverEntity toEntity() {
        return this;
    }

    public static UserPasswordRecoverDoc toDoc(UserPasswordRecoverEntity userPasswordRecoverEntity) {
        return UserPasswordRecoverDoc.builder()
                .id(userPasswordRecoverEntity.getId())
                .counter(userPasswordRecoverEntity.getCounter())
                .retryLimit(userPasswordRecoverEntity.getRetryLimit())
                .retryLeft(userPasswordRecoverEntity.getRetryLeft())
                .isRecovered(userPasswordRecoverEntity.getIsRecovered())
                .userId(userPasswordRecoverEntity.getUserId())
                .code(userPasswordRecoverEntity.getCode())
                .expiredAt(userPasswordRecoverEntity.getExpiredAt())
                .expiredTime(userPasswordRecoverEntity.getExpiredTime())
                .build();
    }
}

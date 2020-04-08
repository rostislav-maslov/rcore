package com.rcore.database.mongo.user.port.model;

import com.rcore.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TODO прописать индекты
 */
@Document("userEntity")
@CompoundIndexes({
        @CompoundIndex(def = "{'_id': 1}", name = "_id_")
})
@SuperBuilder
@Getter
public class UserDoc extends UserEntity {

    public UserDoc() {
    }

    public UserEntity toEntity(){
        return this;
    }

    public static UserDoc toDoc(UserEntity userEntity) {
        return UserDoc.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .fails(userEntity.getFails())
                .firstName(userEntity.getFirstName())
                .fullName(userEntity.getFullName())
                .lastFailDate(userEntity.getLastFailDate())
                .lastName(userEntity.getLastName())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .phoneNumber(userEntity.getPhoneNumber())
                .roles(userEntity.getRoles())
                .secondName(userEntity.getSecondName())
                .userStatus(userEntity.getUserStatus())
                .socialAccounts(userEntity.getSocialAccounts())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .timeZone(userEntity.getTimeZone())
                .build();
    }

}

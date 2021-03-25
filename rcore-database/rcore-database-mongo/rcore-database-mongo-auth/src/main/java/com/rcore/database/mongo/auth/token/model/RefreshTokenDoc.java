package com.rcore.database.mongo.auth.token.model;

import com.rcore.database.mongo.auth.credential.model.CredentialDoc;
import com.rcore.database.mongo.commons.document.BaseDocument;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
@Data
public class RefreshTokenDoc extends BaseDocument {

    @DBRef
    private CredentialDoc credential;
    private Long expireTimeRefreshToken;
    private Long expireTimeAccessToken;
    private LocalDateTime expireAt;
    private RefreshTokenEntity.Status status;
    private String createFromTokenId;
    private RefreshTokenEntity.CreateFrom createFromType;
    private String salt;
}

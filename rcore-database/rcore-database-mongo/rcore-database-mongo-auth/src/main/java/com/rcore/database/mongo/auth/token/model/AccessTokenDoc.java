package com.rcore.database.mongo.auth.token.model;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
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
public class AccessTokenDoc extends BaseDocument {
    
    @DBRef
    private CredentialEntity credential;
    private LocalDateTime expireAt;
    private RefreshTokenEntity.Status status;
    private String createByRefreshTokenId;
    private String sign;
}

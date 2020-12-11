package com.rcore.domain.auth.token.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.security.model.RefreshTokenData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Токен для обновления токена авторизации
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RefreshTokenEntity extends BaseEntity {

    public enum CreateFrom {
        LOGIN, REFRESH;
    }

    public enum Status {
        ACTIVE, INACTIVE, EXPIRED
    }

    private String id;
    private String credentialId;
    private Long expireTimeRefreshToken = 365 * 24 * 60 * 60 * 1000l;
    private Long expireTimeAccessToken = 1 * 24 * 60 * 60 * 1000l;

    private LocalDateTime expireAt = LocalDateTime.now();
    private Status status = Status.ACTIVE;

    private String createFromTokenId;
    private CreateFrom createFromType;

    private String salt;

    public Boolean isActive() {
        if (status.equals(Status.ACTIVE) == false) return false;
        if (LocalDateTime.now().isAfter(expireAt)) return false;

        return true;
    }

    public void deactivate() {
        this.status = Status.INACTIVE;
    }

    public void expire() {
        this.status = Status.EXPIRED;
    }

    public RefreshTokenData toRefreshTokenData() {
        return new RefreshTokenData(this.getId(), this.getCredentialId(), this.getCreatedAt(), this.getExpireAt());
    }
}

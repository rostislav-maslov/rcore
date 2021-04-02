package com.rcore.domain.token.entity;

import com.rcore.domain.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
    private String userId;
    private Long expireTimeRefreshToken = 365 * 24 * 60 * 60 * 1000l;
    private Long expireTimeAccessToken = 1 * 24 * 60 * 60 * 1000l;

    private LocalDateTime expireAt = LocalDateTime.now();
    private Status status = Status.ACTIVE;

    private String createFromTokenId;
    private CreateFrom createFromType;

    private String salt;

    public Boolean isActive() {
        if (!status.equals(Status.ACTIVE)) return false;
        if (LocalDateTime.now().isAfter(expireAt)) return false;

        return true;
    }
}

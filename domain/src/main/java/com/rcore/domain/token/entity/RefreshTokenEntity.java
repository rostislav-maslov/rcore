package com.rcore.domain.token.entity;

import com.rcore.domain.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RefreshTokenEntity extends BaseEntity {

    public enum CreateFrom {
        LOGIN, REFRESH;
    }

    public enum Status {
        ACTIVE, INACTIVE
    }

    private String id;
    private String userId;
    private Long expireTimeRefreshToken = 60 * 24 * 60 * 60 * 1000l;
    private Long expireTimeAccessToken = 2 * 24 * 60 * 60 * 1000l;

    private Date expireAt = new Date();
    private Status status = Status.ACTIVE;

    private String createFromTokenId;
    private CreateFrom createFromType;

    private String salt;

    public Boolean isActive() {
        if (status.equals(Status.ACTIVE) == false) return false;
        if (new Date().getTime() > expireAt.getTime()) return false;

        return true;
    }
}

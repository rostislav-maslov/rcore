package com.rcore.adapter.domain.token.dto;

import com.rcore.domain.token.entity.RefreshTokenEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenDTO {
    private String id;
    private String userId;
    private Long expireTimeRefreshToken;
    private Long expireTimeAccessToken;
    private LocalDateTime expireAt;
    private RefreshTokenEntity.Status status;
    private String createFromTokenId;
    private RefreshTokenEntity.CreateFrom createFromType;
    private String salt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String timeZone;

    public Boolean isActive() {
        if (status.equals(RefreshTokenEntity.Status.ACTIVE) == false) return false;
        if (LocalDateTime.now().isAfter(expireAt)) return false;

        return true;
    }
}

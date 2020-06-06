package com.rcore.adapter.domain.token.dto;

import com.rcore.domain.access.entity.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenDTO {
    private String id;
    private String userId;
    private Set<Access> accesses;
    private LocalDateTime expireAt;
    private String createFromRefreshTokenId;
    private String sign;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String timeZone;
}

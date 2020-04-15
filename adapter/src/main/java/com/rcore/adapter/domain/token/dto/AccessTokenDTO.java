package com.rcore.adapter.domain.token.dto;

import com.rcore.domain.role.entity.Role;
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
    private Set<Role> roles;
    private LocalDateTime expireAt;
    private String createFromRefreshTokenId;
    private String sign;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String timeZone;
}

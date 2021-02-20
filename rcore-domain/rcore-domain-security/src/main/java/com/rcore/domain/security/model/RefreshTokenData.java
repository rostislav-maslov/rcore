package com.rcore.domain.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RefreshTokenData {
    private String id;
    private String credentialId;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}

package com.rcore.domain.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RefreshTokenData {
    private String id;
    private String accessTokenId;
    private String credentialId;
    private LocalDateTime expiredAt;
}

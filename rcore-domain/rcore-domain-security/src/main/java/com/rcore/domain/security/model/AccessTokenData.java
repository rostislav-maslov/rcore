package com.rcore.domain.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AccessTokenData {
    private String id;
    private String credentialId;
    private LocalDateTime expiredAt;
}

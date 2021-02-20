package com.rcore.domain.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AccessTokenData {
    private String id;
    private String credentialId;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}

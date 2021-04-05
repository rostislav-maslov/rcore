package com.rcore.domain.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AccessTokenData {
    private String id;
    private String credentialId;
    private List<CredentialDetails.Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}

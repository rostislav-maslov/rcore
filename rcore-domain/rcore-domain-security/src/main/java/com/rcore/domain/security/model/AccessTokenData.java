package com.rcore.domain.security.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class AccessTokenData {
    private String id;
    private String credentialId;
    private List<CredentialDetails.Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}

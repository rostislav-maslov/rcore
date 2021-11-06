package com.rcore.domain.security.model;

import lombok.*;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccessTokenData {
    private String id;
    private String credentialId;
    private List<CredentialDetails.Role> roles;
    private Instant createdAt;
    private Instant expiredAt;
}

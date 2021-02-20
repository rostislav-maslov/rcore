package com.rcore.domain.auth.token.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenPair {
    private AccessTokenEntity accessToken;
    private RefreshTokenEntity refreshToken;
}

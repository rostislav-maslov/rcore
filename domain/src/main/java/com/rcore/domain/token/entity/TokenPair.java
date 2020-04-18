package com.rcore.domain.token.entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenPair {
    private AccessTokenEntity accessToken;
    private RefreshTokenEntity refreshToken;
}

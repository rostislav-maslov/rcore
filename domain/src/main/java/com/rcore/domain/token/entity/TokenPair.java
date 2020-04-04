package com.rcore.domain.token.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenPair {
    private AccessTokenEntity accessToken;
    private RefreshTokenEntity refreshToken;
}

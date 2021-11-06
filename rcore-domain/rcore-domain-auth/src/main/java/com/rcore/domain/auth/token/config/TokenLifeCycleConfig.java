package com.rcore.domain.auth.token.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class TokenLifeCycleConfig {
    private Long accessTokenLifetimeInSeconds = 10 * 60L;
    private Long refreshTokenLifetimeInSeconds = 365 * 24 * 60 * 60L;
}

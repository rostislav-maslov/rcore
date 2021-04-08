package com.rcore.domain.token.entity;

import com.rcore.commons.utils.StringUtils;

import java.util.Objects;

public final class TokensLifeCycle {
    private static TokensLifeCycle INSTANCE;
    private final Long accessTokenLifeCycle;
    private final Long refreshTokenLifeCycle;

    public TokensLifeCycle(Long accessTokenLifeCycle, Long refreshTokenLifeCycle) {
        this.accessTokenLifeCycle = accessTokenLifeCycle;
        this.refreshTokenLifeCycle = refreshTokenLifeCycle;
    }

    public static TokensLifeCycle getInstance(String accessTokenLifeCycle, String refreshTokenLifeCycle) {
        if (Objects.isNull(INSTANCE)) {
            if (StringUtils.hasText(accessTokenLifeCycle) && StringUtils.hasText(refreshTokenLifeCycle))
                INSTANCE = new TokensLifeCycle(Long.parseLong(accessTokenLifeCycle), Long.parseLong(refreshTokenLifeCycle));
            else
                INSTANCE = new TokensLifeCycle(86400000L, 31536000000L);
        }
        return INSTANCE;
    }

    public Long getAccessTokenLifeCycle() {
        return accessTokenLifeCycle;
    }

    public Long getRefreshTokenLifeCycle() {
        return refreshTokenLifeCycle;
    }
}

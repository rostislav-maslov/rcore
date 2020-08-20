package com.rcore.domain.token.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.token.entity.AccessTokenEntity;

public interface AccessTokenRepository extends CRUDRepository<String, AccessTokenEntity> {
    void expireAccessToken(String accessTokenId);
    void expireAllAccessTokenByRefreshTokenId(String refreshTokenId);
    void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId);
}

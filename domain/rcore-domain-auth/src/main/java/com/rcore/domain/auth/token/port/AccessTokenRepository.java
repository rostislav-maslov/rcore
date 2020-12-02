package com.rcore.domain.auth.token.port;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.SearchFilters;

public interface AccessTokenRepository extends CRUDRepository<String, AccessTokenEntity, SearchFilters> {
    void expireAccessToken(String accessTokenId);
    void expireAllAccessTokenByRefreshTokenId(String refreshTokenId);
    void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId);
}

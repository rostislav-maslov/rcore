package com.rcore.domain.auth.token.port;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.port.filter.AccessTokenFilters;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;

public interface AccessTokenRepository extends CRUDRepository<String, AccessTokenEntity, AccessTokenFilters> {
    void expireAccessToken(String accessTokenId);
    void expireAllAccessTokenByRefreshTokenId(String refreshTokenId);
    void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId);
}

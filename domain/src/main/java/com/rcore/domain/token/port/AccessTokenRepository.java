package com.rcore.domain.token.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.filters.AccessTokenFilters;

import java.util.List;

public interface AccessTokenRepository extends CRUDRepository<String, AccessTokenEntity> {
    void expireAccessToken(String accessTokenId);
    void refreshedAccessToken(String accessTokenId);
    void refreshedAllActiveAccessTokensByRefreshId(String createFromRefreshTokenId);
    void expireAllAccessTokenByRefreshTokenId(String refreshTokenId);
    void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId);
    SearchResult<AccessTokenEntity> findWithFilters(AccessTokenFilters filters);
}

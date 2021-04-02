package com.rcore.domain.token.usecase;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.filters.AccessTokenFilters;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ViewAccessTokenUseCase {

    private final UserRepository userRepository;
    private final AccessTokenStorage accessTokenStorage;

    public SearchResult<AccessTokenEntity> findWithFilters(AccessTokenFilters filters) {
        return accessTokenStorage.findWithFilters(filters);
    }

    public Optional<AccessTokenEntity> findTokenById(String id) {
        return accessTokenStorage.findTokenById(id);
    }

    public Optional<AccessTokenEntity> findTokenByJWT(String jwtToken) {
        return accessTokenStorage.findTokenByJWT(jwtToken);
    }

    public String findJWTById(String id) {
        return accessTokenStorage.findJWTById(id);
    }

}

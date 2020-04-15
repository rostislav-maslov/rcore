package com.rcore.restapi.security.support;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.restapi.security.model.AnonymousTokenAuthentication;
import org.springframework.security.core.Authentication;

public interface AuthenticationMediator<T> {

    Authentication getAuthentication();

    T getUser();

    AccessTokenDTO getAccessToken();

    default boolean isAnonymousUser() {
        Authentication authentication = getAuthentication();
        return authentication instanceof AnonymousTokenAuthentication;
    }

}

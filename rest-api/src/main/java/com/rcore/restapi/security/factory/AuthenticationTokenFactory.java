package com.rcore.restapi.security.factory;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.restapi.security.model.AnonymousTokenAuthentication;
import com.rcore.restapi.security.model.TokenAuthentication;
import org.springframework.security.core.Authentication;

import java.util.Objects;

public class AuthenticationTokenFactory {

    private static String ANONYMOUS_TOKEN = "_anonymous";

    public static Authentication ofRawToken(AccessTokenDTO token) {
        if (Objects.isNull(token))
            return ofAnonymous();
        else return TokenAuthentication.ofToken(token);
    }

    private static Authentication ofAnonymous() {
        return new AnonymousTokenAuthentication(ANONYMOUS_TOKEN);
    }
}

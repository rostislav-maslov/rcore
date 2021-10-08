package com.rcore.rest.api.spring.security;

import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.CredentialIdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof TokenAuthentication) {
            return authentication;
        } else {
            authentication.setAuthenticated(false);
            return authentication;
        }
    }
}

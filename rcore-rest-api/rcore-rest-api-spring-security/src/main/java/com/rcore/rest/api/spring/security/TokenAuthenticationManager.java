package com.rcore.rest.api.spring.security;

import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenAuthenticationManager implements AuthenticationManager {

    private final CredentialService credentialService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (authentication instanceof TokenAuthentication && ((TokenAuthentication) authentication).getToken() != null && !authentication.isAuthenticated()) {
                CredentialDetails credentialDetails = credentialService.getCredentialByToken(((TokenAuthentication) authentication).getToken());
                CredentialPrincipal credentialPrincipal = CredentialPrincipal.from(credentialDetails);
                return new TokenAuthentication(((TokenAuthentication) authentication).getToken(), credentialPrincipal.getAuthorities(), true, credentialPrincipal);
            } else {
                authentication.setAuthenticated(false);
                return authentication;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

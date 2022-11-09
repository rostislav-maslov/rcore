package com.rcore.rest.api.spring.security;

import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.AccessChecker;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultAccessChecker implements AccessChecker {

    private final CredentialIdentityService credentialIdentityService;
    private final TokenParser<AccessTokenData> accessTokenDataTokenParser;

    @Override
    public CredentialDetails checkAccessByToken(String token, String requestType, String requestPath, String serviceName) {
        return credentialIdentityService.getCredentialByToken(token);
    }

    @Override
    public boolean tokenIsValid(String token) {
        try {
            accessTokenDataTokenParser.parseWithValidating(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

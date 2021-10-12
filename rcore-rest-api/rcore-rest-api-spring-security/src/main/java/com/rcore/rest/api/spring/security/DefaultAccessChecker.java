package com.rcore.rest.api.spring.security;

import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.AccessChecker;
import com.rcore.domain.security.port.CredentialIdentityService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultAccessChecker implements AccessChecker {

    private final CredentialIdentityService credentialIdentityService;

    @Override
    public CredentialDetails checkAccessByToken(String token, String requestType, String requestPath, String serviceName) {
        return credentialIdentityService.getCredentialByToken(token);
    }
}

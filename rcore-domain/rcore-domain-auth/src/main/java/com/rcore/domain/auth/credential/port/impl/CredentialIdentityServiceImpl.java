package com.rcore.domain.auth.credential.port.impl;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.security.exceptions.AuthenticationException;
import com.rcore.domain.security.exceptions.CredentialNotFoundException;
import com.rcore.domain.security.exceptions.TokenIsExpiredException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.domain.security.port.CredentialService;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CredentialIdentityServiceImpl implements CredentialIdentityService {

    private final TokenParser<AccessTokenData> tokenParser;
    private final AccessTokenRepository accessTokenRepository;
    private final CredentialService credentialService;

    @Override
    public CredentialDetails getCredentialByToken(String token) throws CredentialNotFoundException {

        if (!StringUtils.hasText(token))
            throw new AuthenticationException();

        AccessTokenData tokenData = tokenParser.parseWithValidating(token);

        AccessTokenEntity accessTokenEntity = accessTokenRepository.findById(tokenData.getId())
                .orElseThrow(AuthenticationException::new);

        if (!accessTokenEntity.isActive())
            throw new TokenIsExpiredException(accessTokenEntity.getId());

        return credentialService.getCredentialById(tokenData.getCredentialId());

    }
}

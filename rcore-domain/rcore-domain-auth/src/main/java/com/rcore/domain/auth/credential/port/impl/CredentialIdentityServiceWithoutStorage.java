package com.rcore.domain.auth.credential.port.impl;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.security.exceptions.*;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;

/**
 * Получения CredentialDetails без обращния в БД. Все данные берутся из токена
 */
@RequiredArgsConstructor
public class CredentialIdentityServiceWithoutStorage implements CredentialIdentityService {

    private final TokenParser<AccessTokenData> tokenParser;

    @Override
    public CredentialDetails getCredentialByToken(String token) throws AuthenticatedCredentialIsBlockedException, CredentialNotFoundException, ParsingTokenException, TokenIsExpiredException {
        if (!StringUtils.hasText(token))
            throw new AuthenticationException();

        AccessTokenData accessTokenData = tokenParser.parseWithValidating(token);

        if (accessTokenData.getCredentialId() == null)
            throw new CredentialNotFoundException();

        return new CredentialDetails(accessTokenData.getCredentialId(), accessTokenData.getRoles());
    }
}

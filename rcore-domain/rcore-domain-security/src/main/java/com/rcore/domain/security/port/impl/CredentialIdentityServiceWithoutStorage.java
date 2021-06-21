package com.rcore.domain.security.port.impl;

import com.rcore.domain.security.exceptions.AccessTokenExpiredException;
import com.rcore.domain.security.exceptions.AccessTokenNotProvidedException;
import com.rcore.domain.security.exceptions.CredentialNotExistException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Получения CredentialDetails без обращния в БД. Все данные берутся из токена
 */
@RequiredArgsConstructor
public class CredentialIdentityServiceWithoutStorage implements CredentialIdentityService {

    private final TokenParser<AccessTokenData> tokenParser;

    @Override
    public CredentialDetails getCredentialByToken(String token) {
        if (token == null || token.length() == 0)
            throw new AccessTokenNotProvidedException();

        AccessTokenData accessTokenData = tokenParser.parseWithValidating(token);

        if (LocalDateTime.now().isAfter(accessTokenData.getExpiredAt()))
            throw new AccessTokenExpiredException();

        if (accessTokenData.getCredentialId() == null)
            throw new CredentialNotExistException();

        return new CredentialDetails(accessTokenData.getCredentialId(), accessTokenData.getRoles());
    }
}

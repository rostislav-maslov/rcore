package com.rcore.rest.api.spring.security.jwt.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;
import com.rcore.domain.security.exceptions.InvalidTokenException;
import com.rcore.domain.security.exceptions.ParsingTokenException;
import com.rcore.domain.security.exceptions.TokenIsExpiredException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAccessTokenParser implements TokenParser<AccessTokenData> {

    private final ObjectMapper objectMapper;
    private final TokenGenerator<AccessTokenData> accessTokenDataTokenGenerator;

    @Override
    public AccessTokenData parseWithValidating(String token) {
        try {
            validateStringToken(token);

            JWSObject jwsObject = JWSObject.parse(token);
            return objectMapper.readValue(jwsObject.getPayload().toString(), AccessTokenData.class);
        } catch (IOException | ParseException e) {
            log.error("Access token data parsing error", e);
            throw new ParsingTokenException();
        }
    }

    private void validateStringToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            AccessTokenData accessTokenData = objectMapper.readValue(jwsObject.getPayload().toString(), AccessTokenData.class);
            String originalToken = accessTokenDataTokenGenerator.generate(accessTokenData);

            if (!originalToken.equals(token))
                throw new InvalidTokenException();

            if (accessTokenData.getExpiredAt().isBefore(LocalDateTime.now()))
                throw new TokenIsExpiredException();

        } catch (Exception e) {
            throw new InvalidTokenException();
        }

    }
}

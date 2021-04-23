package com.rcore.rest.api.spring.security.jwt.refresh;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;
import com.rcore.domain.security.exceptions.InvalidTokenException;
import com.rcore.domain.security.exceptions.ParsingTokenException;
import com.rcore.domain.security.exceptions.TokenIsExpiredException;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
public class JwtRefreshTokenParser implements TokenParser<RefreshTokenData> {

    private final ObjectMapper objectMapper;
    private final TokenGenerator<RefreshTokenData> refreshTokenDataTokenGenerator;

    @Override
    public RefreshTokenData parseWithValidating(String token) {
        try {
            validateStringToken(token);

            JWSObject jwsObject = JWSObject.parse(token);
            return objectMapper.readValue(jwsObject.getPayload().toString(), RefreshTokenData.class);
        } catch (IOException | ParseException e) {
            log.error("Refresh token data parsing error", e);
            throw new ParsingTokenException();
        }
    }

    private void validateStringToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            RefreshTokenData refreshTokenData = objectMapper.readValue(jwsObject.getPayload().toString(), RefreshTokenData.class);
            String originalToken = refreshTokenDataTokenGenerator.generate(refreshTokenData);

            if (!originalToken.equals(token))
                throw new InvalidTokenException();

            if (refreshTokenData.getExpiredAt().isBefore(LocalDateTime.now()))
                throw new TokenIsExpiredException();

        } catch (Exception e) {
            throw new InvalidTokenException();
        }

    }
}

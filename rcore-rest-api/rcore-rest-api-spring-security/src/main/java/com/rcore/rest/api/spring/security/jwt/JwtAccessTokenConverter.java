package com.rcore.rest.api.spring.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.rcore.domain.security.exceptions.ConvertingTokenException;
import com.rcore.domain.security.exceptions.InvalidTokenException;
import com.rcore.domain.security.exceptions.ParsingTokenException;
import com.rcore.domain.security.exceptions.TokenIsExpiredException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.TokenConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAccessTokenConverter implements TokenConverter<AccessTokenData> {

    @Value("${foodtechlab.security.jwt.secret}")
    private String secret;

    private final ObjectMapper objectMapper;

    @Override
    public String convert(AccessTokenData token) throws ConvertingTokenException {
        try {
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(objectMapper.writeValueAsString(token)));
            jwsObject.sign(new MACSigner(secret));
            return jwsObject.serialize();
        } catch (Exception e) {
            log.error("Access token data converting error", e);
            throw new ConvertingTokenException();
        }
    }

    @Override
    public AccessTokenData parse(String token) throws ParsingTokenException {
        try {
            validateStringToken(token);

            JWSObject jwsObject = JWSObject.parse(token);
            return objectMapper.readValue(jwsObject.getPayload().toString(), AccessTokenData.class);
        } catch (IOException | ParseException e) {
            log.error("Access token data parsing error", e);
            throw new ParsingTokenException();
        }
    }

    @Override
    public void validateStringToken(String token) throws InvalidTokenException {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            AccessTokenData accessTokenData = objectMapper.readValue(jwsObject.getPayload().toString(), AccessTokenData.class);
            String originalToken = convert(accessTokenData);

            if (!originalToken.equals(token))
                throw new InvalidTokenException();

            if (accessTokenData.getExpiredAt().isBefore(LocalDateTime.now()))
                throw new TokenIsExpiredException();

        } catch (Exception e) {
            throw new InvalidTokenException();
        }

    }
}

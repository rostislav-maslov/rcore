package com.rcore.rest.api.spring.security.jwt.refresh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.rcore.domain.security.exceptions.RefreshTokenMalformedException;
import com.rcore.domain.security.exceptions.RefreshTokenModifiedException;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;

@RequiredArgsConstructor
@Slf4j
public class JwtRefreshTokenParser implements TokenParser<RefreshTokenData> {

    @Value("${foodtechlab.security.jwt.secret}")
    private String secret;
    private final ObjectMapper objectMapper;
    private final TokenGenerator<RefreshTokenData> tokenGenerator;

    @Override
    public RefreshTokenData parseWithValidating(String token) {
        try {
            validateStringToken(token);

            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secret);

            boolean isValid = signedJWT.verify(verifier);
            if (!isValid)
                throw new RefreshTokenModifiedException();

            JWSObject jwsObject = JWSObject.parse(token);
            return objectMapper.readValue(jwsObject.getPayload().toString(), RefreshTokenData.class);
        } catch (JOSEException | JsonProcessingException | ParseException e) {
            e.printStackTrace();
            throw new RefreshTokenMalformedException();
        }
    }

    @SneakyThrows
    private void validateStringToken(String token) {
        var jwsObject = JWSObject.parse(token);
        var refreshTokenData = objectMapper.readValue(jwsObject.getPayload().toString(), RefreshTokenData.class);
        var originalToken = tokenGenerator.generate(refreshTokenData);

        if (!originalToken.equals(token))
            throw new RefreshTokenModifiedException();

    }
}

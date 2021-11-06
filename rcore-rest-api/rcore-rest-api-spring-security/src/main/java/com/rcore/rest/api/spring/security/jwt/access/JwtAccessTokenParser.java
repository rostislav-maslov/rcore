package com.rcore.rest.api.spring.security.jwt.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.rcore.domain.security.exceptions.AccessTokenMalformedException;
import com.rcore.domain.security.exceptions.AccessTokenModifiedException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;

@RequiredArgsConstructor
@Slf4j
public class JwtAccessTokenParser implements TokenParser<AccessTokenData> {

    @Value("${foodtechlab.security.jwt.secret}")
    private String secret;
    private final ObjectMapper objectMapper;
    private final TokenGenerator<AccessTokenData> accessTokenDataTokenGenerator;

    @Override
    public AccessTokenData parseWithValidating(String token) {
        try {
            validateStringToken(token);

            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secret);

            boolean isValid = signedJWT.verify(verifier);
            if (!isValid)
                throw new AccessTokenModifiedException();

            JWSObject jwsObject = JWSObject.parse(token);
            return objectMapper.readValue(jwsObject.getPayload().toString(), AccessTokenData.class);
        } catch (JOSEException | JsonProcessingException | ParseException e) {
            e.printStackTrace();
            throw new AccessTokenMalformedException();
        }
    }

    @SneakyThrows
    private void validateStringToken(String token) {
        JWSObject jwsObject = JWSObject.parse(token);
        AccessTokenData accessTokenData = objectMapper.readValue(jwsObject.getPayload().toString(), AccessTokenData.class);
        String originalToken = accessTokenDataTokenGenerator.generate(accessTokenData);

        if (!originalToken.equals(token))
            throw new AccessTokenModifiedException();

    }
}

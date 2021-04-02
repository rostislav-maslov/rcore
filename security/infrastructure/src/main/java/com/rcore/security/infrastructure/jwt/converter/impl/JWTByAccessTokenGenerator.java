package com.rcore.security.infrastructure.jwt.converter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import com.rcore.security.infrastructure.utils.ObjectMapperUtils;

import java.time.LocalDateTime;

public class JWTByAccessTokenGenerator implements AuthTokenGenerator<AccessTokenDTO> {

    private final ObjectMapper objectMapper = ObjectMapperUtils.localDateTimeMapper();

    @Override
    public String generate(AccessTokenDTO accessTokenDTO, String secret) throws TokenGenerateException {
        try {
            accessTokenDTO.setAccesses(null);

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(objectMapper.writeValueAsString(accessTokenDTO)));
            jwsObject.sign(new MACSigner(secret));
            return jwsObject.serialize();
        } catch (Exception e) {
            throw new TokenGenerateException();
        }
    }

    @Override
    public AccessTokenDTO parseToken(String token, String secret) throws InvalidTokenFormatException, TokenGenerateException, AuthenticationException {
        AccessTokenDTO accessTokenDTO = null;
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            accessTokenDTO = objectMapper.readValue(jwsObject.getPayload().toString(), AccessTokenDTO.class);
        } catch (Exception e) {
            throw new InvalidTokenFormatException();
        }
        //Проверяем подлинность переданного токена
        if (!generate(accessTokenDTO, secret).equals(token))
            throw new AuthenticationException();
        return accessTokenDTO;
    }


}

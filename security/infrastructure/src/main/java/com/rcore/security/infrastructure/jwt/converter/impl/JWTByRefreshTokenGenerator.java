package com.rcore.security.infrastructure.jwt.converter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import com.rcore.security.infrastructure.utils.ObjectMapperUtils;

public class JWTByRefreshTokenGenerator implements AuthTokenGenerator<RefreshTokenDTO> {

    private final ObjectMapper objectMapper = ObjectMapperUtils.localDateTimeMapper();

    @Override
    public String generate(RefreshTokenDTO refreshTokenDTO, String secret) throws TokenGenerateException {
        try {
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(objectMapper.writeValueAsString(refreshTokenDTO)));
            jwsObject.sign(new MACSigner(secret));
            return jwsObject.serialize();
        } catch (Exception e) {
            throw new TokenGenerateException();
        }
    }

    @Override
    public RefreshTokenDTO parseToken(String token, String secret) throws TokenGenerateException, AuthenticationException, UserNotExistException {
        RefreshTokenDTO refreshTokenDTO = null;
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            refreshTokenDTO = objectMapper.readValue(jwsObject.getPayload().toString(), RefreshTokenDTO.class);
        } catch (Exception e) {
            throw new UserNotExistException();
        }

        if (!generate(refreshTokenDTO, secret).equals(token))
            throw new AuthenticationException();
        return refreshTokenDTO;
    }
}

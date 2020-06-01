package com.rcore.security.infrastructure.jwt.converter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;

public class JWTByRefreshTokenGenerator implements AuthTokenGenerator<RefreshTokenDTO> {

    @Override
    public String generate(RefreshTokenDTO refreshTokenDTO, String secret) throws TokenGenerateException {
        try {
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(new ObjectMapper().writeValueAsString(refreshTokenDTO)));
            jwsObject.sign(new MACSigner(secret));
            return jwsObject.serialize();
        } catch (Exception e) {
            throw new TokenGenerateException();
        }
    }

    @Override
    public RefreshTokenDTO parseToken(String token, String secret) throws InvalidTokenFormatException {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            return new ObjectMapper().readValue(jwsObject.getPayload().toString(), RefreshTokenDTO.class);
        } catch (Exception e) {
            throw new InvalidTokenFormatException();
        }
    }
}

package com.rcore.security.infrastructure.jwt.converter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.jwt.exceptions.JWTGenerateException;
import com.rcore.security.infrastructure.jwt.exceptions.JWTParseException;

public class JWTByRefreshTokenGenerator implements AuthTokenGenerator<RefreshTokenDTO> {

    @Override
    public String generate(RefreshTokenDTO refreshTokenDTO, String secret) throws JWTGenerateException {
        try {
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(new ObjectMapper().writeValueAsString(refreshTokenDTO)));
            jwsObject.sign(new MACSigner(secret));
            return jwsObject.serialize();
        } catch (Exception e) {
            throw new JWTGenerateException();
        }
    }

    @Override
    public RefreshTokenDTO parseToken(String token, String secret) throws JWTParseException {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            jwsObject.getPayload();
            return new ObjectMapper().readValue(token, RefreshTokenDTO.class);
        } catch (Exception e) {
            throw new JWTParseException();
        }
    }
}

package com.rcore.security.infrastructure.jwt.converter.impl;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class JWTByAccessTokenGenerator implements AuthTokenGenerator<AccessTokenDTO> {

    private final ObjectMapper objectMapper;

    public JWTByAccessTokenGenerator() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializerByType(LocalDateTime.class, new ToStringSerializer());
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        this.objectMapper = builder.build();
    }

    @Override
    public String generate(AccessTokenDTO accessTokenDTO, String secret) throws TokenGenerateException {
        try {
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(objectMapper.writeValueAsString(accessTokenDTO)));
            jwsObject.sign(new MACSigner(secret));
            return jwsObject.serialize();
        } catch (Exception e) {
            throw new TokenGenerateException();
        }
    }

    @Override
    public AccessTokenDTO parseToken(String token, String secret) throws InvalidTokenFormatException {
        if (!StringUtils.hasText(token))
            return null;

        try {
            JWSObject jwsObject = JWSObject.parse(token);
            return objectMapper.readValue(jwsObject.getPayload().toString(), AccessTokenDTO.class);
        } catch (Exception e) {
            throw new InvalidTokenFormatException();
        }
    }


}

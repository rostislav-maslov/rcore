package com.rcore.rest.api.spring.security.jwt.refresh;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
@Slf4j
public class JwtRefreshTokenGenerator implements TokenGenerator<RefreshTokenData> {

    @Value("${foodtechlab.security.jwt.secret}")
    private String secret;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public String generate(RefreshTokenData token) {
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(objectMapper.writeValueAsString(token)));
        jwsObject.sign(new MACSigner(secret));
        return jwsObject.serialize();
    }

}

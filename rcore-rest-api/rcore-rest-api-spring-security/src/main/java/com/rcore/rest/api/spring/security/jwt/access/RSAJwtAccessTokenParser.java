package com.rcore.rest.api.spring.security.jwt.access;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import com.rcore.domain.security.exceptions.InvalidTokenException;
import com.rcore.domain.security.exceptions.ParsingTokenException;
import com.rcore.domain.security.exceptions.TokenIsExpiredException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class RSAJwtAccessTokenParser implements TokenParser<AccessTokenData> {

    private final String rsaPublicKey;
    private final ObjectMapper objectMapper;

    @Override
    public AccessTokenData parseWithValidating(String token) {
        try {
            RSAKey rsa = (RSAKey) JWK.parseFromPEMEncodedObjects(rsaPublicKey);
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new RSASSAVerifier(rsa);

            boolean isValid = signedJWT.verify(verifier);

            if (!isValid)
                throw new InvalidTokenException();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime expiredAt = LocalDateTime.parse(signedJWT.getJWTClaimsSet().getClaim("expiredAt").toString(), dateTimeFormatter);

            if (expiredAt.isBefore(LocalDateTime.now()))
                throw new TokenIsExpiredException();

            return AccessTokenData.builder()
                    .id(signedJWT.getJWTClaimsSet().getClaim("tokenId").toString())
                    .credentialId(signedJWT.getJWTClaimsSet().getClaim("credentialId").toString())
                    .expiredAt(expiredAt)
                    .createdAt(LocalDateTime.parse(signedJWT.getJWTClaimsSet().getClaim("createdAt").toString(), dateTimeFormatter))
                    .roles(objectMapper.readValue(signedJWT.getJWTClaimsSet().getClaim("roles").toString(), new TypeReference<List<CredentialDetails.Role>>() {}))
                    .build();
        } catch (Exception e) {
            if (e instanceof TokenIsExpiredException)
                throw (TokenIsExpiredException) e;

            log.error("Access token data parsing error", e);
            throw new ParsingTokenException();
        }
    }
}

package com.rcore.rest.api.spring.security.jwt.refresh;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
public class RSAJwtRefreshTokenGenerator implements TokenGenerator<RefreshTokenData> {

    private final String rsaPrivateKey;

    @SneakyThrows
    @Override
    public String generate(RefreshTokenData token) {
        RSAKey rsa = (RSAKey) JWK.parseFromPEMEncodedObjects(rsaPrivateKey);
        // Create RSA-signer with the private key
        JWSSigner signer = new RSASSASigner(rsa);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

        // Prepare JWT with claims set
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("credentialId", token.getCredentialId())
                .claim("expiredAt", dateTimeFormatter.format(token.getExpiredAt()))
                .claim("tokenId", token.getId())
                .claim("createdAt", dateTimeFormatter.format(token.getCreatedAt()))
                .claim("roles", token.getRoles())
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256)
                        .keyID(rsa.getKeyID())
                        .build(),
                claimsSet
        );
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }
}

package com.rcore.rest.api.spring.security.jwt.access;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.rcore.domain.security.exceptions.ConvertingTokenException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class RSAJwtAccessTokenGenerator implements TokenGenerator<AccessTokenData> {

    private final String rsaPrivateKey;

    @Override
    public String generate(AccessTokenData token) throws ConvertingTokenException {
        try {
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
        } catch (Exception e) {
            log.error("Access token data converting error", e);
            throw new ConvertingTokenException();
        }
    }
}

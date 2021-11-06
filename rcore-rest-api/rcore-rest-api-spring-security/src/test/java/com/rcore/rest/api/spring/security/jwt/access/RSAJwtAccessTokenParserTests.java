package com.rcore.rest.api.spring.security.jwt.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.domain.security.port.TokenParser;
import com.rcore.rest.api.spring.security.jwt.KeyTestsUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RSAJwtAccessTokenParserTests {

    @ParameterizedTest
    @ArgumentsSource(TestData.RSAJwtAccessTokenParser_Successful_Args.class)
    void RSAJwtAccessTokenParser_Successful(AccessTokenData data) throws NoSuchAlgorithmException {
        Map<String, String> keyPair = KeyTestsUtils.generateRSAKeyPair();
        TokenGenerator<AccessTokenData> accessTokenDataTokenGenerator = new RSAJwtAccessTokenGenerator(keyPair.get("privateKey"));
        TokenParser<AccessTokenData> accessTokenDataTokenParser = new RSAJwtAccessTokenParser(keyPair.get("publicKey"), new ObjectMapper());
        String token = accessTokenDataTokenGenerator.generate(data);

        assertNotNull(token);

        AccessTokenData parsedToken = accessTokenDataTokenParser.parseWithValidating(token);

        assertNotNull(parsedToken);
        assertEquals(parsedToken, data);
    }

    public static class TestData {

        public static class RSAJwtAccessTokenParser_Successful_Args implements ArgumentsProvider {

            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
                return Stream.of(
                        Arguments.of(AccessTokenData.builder()
                                .id(UUID.randomUUID().toString())
                                .createdAt(Instant.now())
                                .expiredAt(Instant.now().plus(1, ChronoUnit.DAYS))
                                .credentialId(UUID.randomUUID().toString())
                                .roles(Collections.singletonList(new CredentialDetails.Role(UUID.randomUUID().toString(), "ADMIN")))
                                .build())
                );
            }
        }

    }
}
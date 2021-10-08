package com.rcore.rest.api.spring.security.jwt.refresh;

import com.nimbusds.jose.JOSEException;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.rest.api.spring.security.jwt.KeyTestsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Stream;

class RSAJwtRefreshTokenGeneratorTests {

    @ParameterizedTest
    @ArgumentsSource(TestData.RSAJwtRefreshTokenGenerator_Successful_Args.class)
    void RSAJwtRefreshTokenGenerator_Successful(RefreshTokenData data) throws JOSEException, NoSuchAlgorithmException {
        TokenGenerator<RefreshTokenData> rsaJwtRefreshTokenGenerator = new RSAJwtRefreshTokenGenerator(KeyTestsUtils.generateRSAKeyPair().get("privateKey"));
        String token = rsaJwtRefreshTokenGenerator.generate(data);

        Assertions.assertNotNull(token);
    }

    public static class TestData {

        public static class RSAJwtRefreshTokenGenerator_Successful_Args implements ArgumentsProvider {

            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
                return Stream.of(
                        Arguments.of(RefreshTokenData.builder()
                                .id(UUID.randomUUID().toString())
                                .createdAt(LocalDateTime.now())
                                .expiredAt(LocalDateTime.now().plusDays(1))
                                .credentialId(UUID.randomUUID().toString())
                                .roles(Collections.singletonList(new CredentialDetails.Role(UUID.randomUUID().toString(), "ADMIN")))
                                .build())
                );
            }
        }

    }

}
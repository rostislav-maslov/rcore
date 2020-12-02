package com.rcore.domain.auth.authorization.config;

import com.rcore.domain.auth.authorization.usecase.all.CreateAuthorizationUseCase;
import com.rcore.domain.auth.authorization.usecase.all.PasswordAuthorizationUseCase;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecase.CreateRefreshTokenUseCase;
import lombok.RequiredArgsConstructor;

public class AuthorizationConfig {

    @RequiredArgsConstructor
    public static class All {

        private final CreateAuthorizationUseCase createAuthorizationUseCase;
        private final CredentialRepository credentialRepository;
        private final PasswordCryptographer passwordCryptographer;
        private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
        private final CreateAccessTokenUseCase createAccessTokenUseCase;

        public PasswordAuthorizationUseCase passwordAuthorizationUseCase() {
            return new PasswordAuthorizationUseCase(createAuthorizationUseCase, credentialRepository, passwordCryptographer, createRefreshTokenUseCase, createAccessTokenUseCase);
        }
    }

    public final All all;

    public AuthorizationConfig(CreateAuthorizationUseCase createAuthorizationUseCase, CredentialRepository credentialRepository, PasswordCryptographer passwordCryptographer, CreateRefreshTokenUseCase createRefreshTokenUseCase, CreateAccessTokenUseCase createAccessTokenUseCase) {
        this.all = new All(createAuthorizationUseCase, credentialRepository, passwordCryptographer, createRefreshTokenUseCase, createAccessTokenUseCase);
    }
}

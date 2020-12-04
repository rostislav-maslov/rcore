package com.rcore.domain.auth.authorization.config;

import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.authorization.usecase.all.*;
import com.rcore.domain.auth.authorization.usecase.secured.SecuredAuthorizationViewUseCase;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.confirmationCode.usecase.all.CreateConfirmationCodeUseCase;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.security.port.CredentialVerifier;
import lombok.RequiredArgsConstructor;

public class AuthorizationConfig {

    @RequiredArgsConstructor
    public static class Secured {

        private final CredentialVerifier credentialVerifier;
        private final AuthorizationRepository authorizationRepository;

        public SecuredAuthorizationViewUseCase viewUseCase() {
            return new SecuredAuthorizationViewUseCase(credentialVerifier, authorizationRepository);
        }

    }

    @RequiredArgsConstructor
    public static class All {

        private final CreateAuthorizationUseCase createAuthorizationUseCase;
        private final CredentialRepository credentialRepository;
        private final PasswordCryptographer passwordCryptographer;
        private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
        private final CreateAccessTokenUseCase createAccessTokenUseCase;
        private final CreateConfirmationCodeUseCase createConfirmationCodeUseCase;
        private final AuthorizationRepository authorizationRepository;
        private final ConfirmationCodeRepository confirmationCodeRepository;
        private final SuccessfulAuthorizationUseCase successfulAuthorizationUseCase;

        public PasswordAuthorizationUseCase passwordAuthorizationUseCase() {
            return new PasswordAuthorizationUseCase(createAuthorizationUseCase, credentialRepository, passwordCryptographer, createRefreshTokenUseCase, createAccessTokenUseCase);
        }

        public InitTwoFactorAuthorizationUseCase initTwoFactorAuthorizationUseCase() {
            return new InitTwoFactorAuthorizationUseCase(createAuthorizationUseCase, credentialRepository, createConfirmationCodeUseCase);
        }

        public ConfirmTwoFactorAuthorizationUseCase confirmTwoFactorAuthorizationUseCase() {
            return new ConfirmTwoFactorAuthorizationUseCase(authorizationRepository, confirmationCodeRepository, credentialRepository, createAccessTokenUseCase, createRefreshTokenUseCase, successfulAuthorizationUseCase);
        }
    }

    public final All all;
    public final Secured secured;

    public AuthorizationConfig(
            CreateAuthorizationUseCase createAuthorizationUseCase,
            CredentialRepository credentialRepository,
            PasswordCryptographer passwordCryptographer,
            CreateRefreshTokenUseCase createRefreshTokenUseCase,
            CreateAccessTokenUseCase createAccessTokenUseCase,
            CreateConfirmationCodeUseCase createConfirmationCodeUseCase,
            AuthorizationRepository authorizationRepository,
            ConfirmationCodeRepository confirmationCodeRepository,
            SuccessfulAuthorizationUseCase successfulAuthorizationUseCase,
            CredentialVerifier credentialVerifier
    ) {
        this.all = new All(createAuthorizationUseCase, credentialRepository, passwordCryptographer, createRefreshTokenUseCase, createAccessTokenUseCase, createConfirmationCodeUseCase, authorizationRepository, confirmationCodeRepository, successfulAuthorizationUseCase);
        this.secured = new Secured(credentialVerifier, authorizationRepository);
    }
}

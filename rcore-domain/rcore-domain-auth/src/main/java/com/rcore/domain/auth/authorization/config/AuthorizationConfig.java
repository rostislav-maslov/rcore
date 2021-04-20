package com.rcore.domain.auth.authorization.config;

import com.rcore.domain.auth.authorization.port.AuthorizationIdGenerator;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.authorization.usecases.*;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.confirmationCode.usecases.CreateConfirmationCodeUseCase;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.credential.usecases.FindCredentialByEmailUseCase;
import com.rcore.domain.auth.credential.usecases.FindCredentialByIdUseCase;
import com.rcore.domain.auth.credential.usecases.FindCredentialByPhoneUseCase;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.auth.token.usecases.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecases.CreateRefreshTokenUseCase;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationConfig {

    private final AuthorizationRepository authorizationRepository;
    private final AuthorizationIdGenerator<?> authorizationIdGenerator;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final FindCredentialByIdUseCase findCredentialByIdUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateConfirmationCodeUseCase createConfirmationCodeUseCase;
    private final FindCredentialByPhoneUseCase findCredentialByPhoneUseCase;
    private final FindCredentialByEmailUseCase findCredentialByEmailUseCase;
    private final TokenParser<AccessTokenData> tokenParser;
    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CredentialRepository credentialRepository;
    private final PasswordCryptographer passwordCryptographer;

    public ConfirmTwoFactorAuthorizationUseCase confirmTwoFactorAuthorizationUseCase() {
        return new ConfirmTwoFactorAuthorizationUseCase(
                confirmationCodeRepository,
                getAuthorizationByIdUseCase(),
                findCredentialByIdUseCase,
                createAccessTokenUseCase,
                createRefreshTokenUseCase,
                transferAuthorizationToSuccessStatusUseCase()
        );
    }

    public CreateAuthorizationUseCase createAuthorizationUseCase() {
        return new CreateAuthorizationUseCase(authorizationRepository, authorizationIdGenerator);
    }

    public FindAuthorizationByIdUseCase getAuthorizationByIdUseCase() {
        return new FindAuthorizationByIdUseCase(authorizationRepository);
    }

    public InitTwoFactorAuthorizationUseCase initTwoFactorAuthorizationUseCase() {
        return new InitTwoFactorAuthorizationUseCase(
                createAuthorizationUseCase(),
                createConfirmationCodeUseCase,
                findCredentialByEmailUseCase,
                findCredentialByPhoneUseCase
        );
    }

    public LogoutUseCase logoutUseCase() {
        return new LogoutUseCase(
                tokenParser,
                refreshTokenRepository,
                accessTokenRepository
        );
    }

    public PasswordAuthorizationUseCase passwordAuthorizationUseCase() {
        return new PasswordAuthorizationUseCase(
                createAuthorizationUseCase(),
                credentialRepository,
                passwordCryptographer,
                createRefreshTokenUseCase,
                createAccessTokenUseCase
        );
    }

    public TransferAuthorizationToSuccessStatusUseCase transferAuthorizationToSuccessStatusUseCase() {
        return new TransferAuthorizationToSuccessStatusUseCase(authorizationRepository);
    }
}

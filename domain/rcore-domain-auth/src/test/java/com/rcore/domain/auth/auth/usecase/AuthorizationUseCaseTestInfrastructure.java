package com.rcore.domain.auth.auth.usecase;

import com.rcore.domain.auth.authorization.config.AuthorizationConfig;
import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationIdGenerator;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.authorization.usecase.all.CreateAuthorizationUseCase;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.credential.port.impl.PasswordCryptographerImpl;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.*;
import com.rcore.domain.auth.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.auth.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecase.CreateRefreshTokenUseCase;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class AuthorizationUseCaseTestInfrastructure {

    protected final AuthorizationRepository authorizationRepository = Mockito.mock(AuthorizationRepository.class);
    protected final AuthorizationIdGenerator authorizationIdGenerator = Mockito.mock(AuthorizationIdGenerator.class);
    protected final CredentialRepository credentialRepository = Mockito.mock(CredentialRepository.class);
    protected final PasswordCryptographer passwordCryptographer = new PasswordCryptographerImpl();
    protected final AccessTokenRepository accessTokenRepository = Mockito.mock(AccessTokenRepository.class);
    protected final AccessTokenIdGenerator accessTokenIdGenerator = Mockito.mock(AccessTokenIdGenerator.class);
    protected final RefreshTokenRepository refreshTokenRepository = Mockito.mock(RefreshTokenRepository.class);
    protected final RefreshTokenIdGenerator refreshTokenIdGenerator = Mockito.mock(RefreshTokenIdGenerator.class);
    protected final TokenSaltGenerator tokenSaltGenerator = new TokenSaltGeneratorImpl();

    protected final AuthorizationConfig authorizationConfig;

    public AuthorizationUseCaseTestInfrastructure() {

        CreateRefreshTokenUseCase createRefreshTokenUseCase = new CreateRefreshTokenUseCase(refreshTokenIdGenerator, refreshTokenRepository, tokenSaltGenerator);

        this.authorizationConfig = new AuthorizationConfig(
                new CreateAuthorizationUseCase(authorizationRepository, authorizationIdGenerator),
                credentialRepository,
                passwordCryptographer,
                createRefreshTokenUseCase,
                new CreateAccessTokenUseCase(accessTokenRepository, accessTokenIdGenerator, createRefreshTokenUseCase)
        );

        initAuthorizationMocks();
        initTokenMocks();
        initTokenMocks();
    }

    private void initAuthorizationMocks() {
        Mockito.when(authorizationRepository.save(any(AuthorizationEntity.class)))
                .thenAnswer(a -> (AuthorizationEntity) a.getArgument(0));

        Mockito.when(authorizationIdGenerator.generate())
                .thenAnswer(a -> UUID.randomUUID().toString());
    }

    private void initTokenMocks() {
        Mockito.when(accessTokenRepository.save(any(AccessTokenEntity.class)))
                .thenAnswer(a -> (AccessTokenEntity) a.getArgument(0));

        Mockito.when(accessTokenIdGenerator.generate())
                .then(a -> UUID.randomUUID().toString());

        Mockito.when(refreshTokenRepository.save(any(RefreshTokenEntity.class)))
                .thenAnswer(a -> (RefreshTokenEntity) a.getArgument(0));

        Mockito.when(refreshTokenIdGenerator.generate())
                .then(a -> UUID.randomUUID().toString());
    }

    private void initCredentialMocks() {
        Mockito.when(credentialRepository.save(any(CredentialEntity.class)))
                .thenAnswer(a -> (CredentialEntity) a.getArgument(0));
    }
}

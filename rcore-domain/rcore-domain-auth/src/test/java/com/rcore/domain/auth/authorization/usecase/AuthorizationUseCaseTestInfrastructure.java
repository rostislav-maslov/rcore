package com.rcore.domain.auth.authorization.usecase;

import com.rcore.domain.auth.authorization.config.AuthorizationConfig;
import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationIdGenerator;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeIdGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.confirmationCode.usecases.CreateConfirmationCodeUseCase;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.credential.port.impl.PasswordCryptographerImpl;
import com.rcore.domain.auth.credential.usecases.FindCredentialByEmailUseCase;
import com.rcore.domain.auth.credential.usecases.FindCredentialByIdUseCase;
import com.rcore.domain.auth.credential.usecases.FindCredentialByPhoneUseCase;
import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.*;
import com.rcore.domain.auth.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.auth.token.usecases.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecases.CreateRefreshTokenUseCase;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenConverter;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
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
    protected final ConfirmationCodeRepository confirmationCodeRepository = Mockito.mock(ConfirmationCodeRepository.class);
    protected final ConfirmationCodeIdGenerator confirmationCodeIdGenerator = Mockito.mock(ConfirmationCodeIdGenerator.class);
    protected final ConfirmationCodeGenerator confirmationCodeGenerator = Mockito.mock(ConfirmationCodeGenerator.class);
    protected final SessionTokenService sessionTokenService = Mockito.mock(SessionTokenService.class);
    protected final TokenConverter<AccessTokenData> accessTokenDataTokenConverter = Mockito.mock(TokenConverter.class);
    protected final TokenConverter<RefreshTokenData> refreshTokenDataTokenConverter = Mockito.mock(TokenConverter.class);
    protected final RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    protected final FindCredentialByIdUseCase findCredentialByIdUseCase = new FindCredentialByIdUseCase(credentialRepository);
    protected final CreateAccessTokenUseCase createAccessTokenUseCase = new CreateAccessTokenUseCase(accessTokenRepository, accessTokenIdGenerator);
    protected final CreateConfirmationCodeUseCase createConfirmationCodeUseCase = new CreateConfirmationCodeUseCase(confirmationCodeRepository, confirmationCodeIdGenerator, confirmationCodeGenerator);
    protected final FindCredentialByPhoneUseCase findCredentialByPhoneUseCase = new FindCredentialByPhoneUseCase(credentialRepository);
    protected final FindCredentialByEmailUseCase findCredentialByEmailUseCase = new FindCredentialByEmailUseCase(credentialRepository);

    protected AuthorizationConfig authorizationConfig;

    protected final static RoleEntity superUserRole = RoleEntity.builder()
            .id(UUID.randomUUID().toString())
            .name("SUPERUSER")
            .hasBoundlessAccess(true)
            .build();

    protected static final CredentialEntity authorizedCredential = CredentialEntity.builder()
            .id(UUID.randomUUID().toString())
            .roles(Arrays.asList(CredentialEntity.Role.builder()
                    .isBlocked(false)
                    .roleId(superUserRole.getId())
                    .build()))
            .build();

    public AuthorizationUseCaseTestInfrastructure() {

        CreateRefreshTokenUseCase createRefreshTokenUseCase = new CreateRefreshTokenUseCase(refreshTokenRepository, refreshTokenIdGenerator, tokenSaltGenerator);

        this.authorizationConfig = new AuthorizationConfig(
                authorizationRepository,
                authorizationIdGenerator,
                confirmationCodeRepository,
                findCredentialByIdUseCase,
                createAccessTokenUseCase,
                createRefreshTokenUseCase,
                createConfirmationCodeUseCase,
                findCredentialByPhoneUseCase,
                findCredentialByEmailUseCase,
                accessTokenDataTokenConverter,
                accessTokenRepository,
                refreshTokenRepository,
                credentialRepository,
                passwordCryptographer
        );

        initAuthorizationMocks();
        initCredentialMocks();
        initTokenMocks();
        initConfirmationCodeMocks();
        initSessionTokenMocks();
        initTokenConverterMocks();
        initRoleMocks();
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

        Mockito.when(credentialRepository.findById(anyString()))
                .thenAnswer(a -> {
                    if (a.getArgument(0).toString().equals(authorizedCredential.getId()))
                        return Optional.of(authorizedCredential);
                    else return Optional.empty();
                });
    }

    private void initConfirmationCodeMocks() {
        Mockito.when(confirmationCodeRepository.save(any(ConfirmationCodeEntity.class)))
                .thenAnswer(a -> (ConfirmationCodeEntity) a.getArgument(0));

        Mockito.when(confirmationCodeIdGenerator.generate())
                .then(a -> UUID.randomUUID().toString());

        Mockito.when(confirmationCodeGenerator.generate())
                .then(a -> UUID.randomUUID().toString());

    }

    private void initSessionTokenMocks() {
        Mockito.when(sessionTokenService.getSessionAccessToken())
                .then(a -> Optional.of(UUID.randomUUID().toString()));
    }

    private void initTokenConverterMocks() {
        Mockito.when(accessTokenDataTokenConverter.convert(any(AccessTokenData.class)))
                .then(a -> UUID.randomUUID().toString());

        Mockito.when(accessTokenDataTokenConverter.parse(anyString()))
                .then(a -> new AccessTokenData(UUID.randomUUID().toString(), authorizedCredential.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(1)));

        Mockito.when(refreshTokenDataTokenConverter.convert(any(RefreshTokenData.class)))
                .then(a -> UUID.randomUUID().toString());

        Mockito.when(refreshTokenDataTokenConverter.parse(anyString()))
                .then(a -> new RefreshTokenData(UUID.randomUUID().toString(), authorizedCredential.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
    }

    private void initRoleMocks() {
        Mockito.when(roleRepository.findById(anyString())).thenAnswer(a -> {
            if (a.getArgument(0).toString().equals(superUserRole.getId()))
                return Optional.of(superUserRole);
            else return Optional.empty();
        });
    }
}

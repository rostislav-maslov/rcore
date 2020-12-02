package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
import com.rcore.domain.auth.authorization.exceptions.InvalidUsernameException;
import com.rcore.domain.auth.authorization.exceptions.PasswordIsRequiredException;
import com.rcore.domain.auth.authorization.exceptions.UsernameIsRequiredException;
import com.rcore.domain.auth.authorization.usecase.all.commands.CreateAuthorizationCommand;
import com.rcore.domain.auth.authorization.usecase.all.commands.PasswordAuthorizationCommand;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialIsBlockedException;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.entity.TokenPair;
import com.rcore.domain.auth.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecase.CreateRefreshTokenUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordAuthorizationUseCase {

    private final CreateAuthorizationUseCase createAuthorizationUseCase;
    private final CredentialRepository credentialRepository;
    private final PasswordCryptographer passwordCryptographer;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;

    public TokenPair login(PasswordAuthorizationCommand passwordAuthorizationCommand) throws UsernameIsRequiredException, PasswordIsRequiredException, BadCredentialsException, CredentialIsBlockedException {
        validate(passwordAuthorizationCommand);

        CredentialEntity credentialEntity = credentialRepository.findByUsername(passwordAuthorizationCommand.getUsername())
                .orElseThrow(BadCredentialsException::new);

        if (passwordCryptographer.validate(passwordAuthorizationCommand.getPassword(), credentialEntity.getPassword(), credentialEntity.getId()) == false) {
            failAuthentication(credentialEntity);
            throw new BadCredentialsException();
        }

        if (credentialEntity.isBlocked()) {
            failAuthentication(credentialEntity);
            throw new CredentialIsBlockedException();
        }

        successAuthentication(credentialEntity);
        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.create(credentialEntity);
        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.create(credentialEntity, refreshTokenEntity);

        createAuthentication(passwordAuthorizationCommand, accessTokenEntity.getId(), refreshTokenEntity.getId());

        return TokenPair.builder()
                .accessToken(accessTokenEntity)
                .refreshToken(refreshTokenEntity)
                .build();
    }

    private void createAuthentication(PasswordAuthorizationCommand passwordAuthorizationCommand, String accessTokenId, String refreshTokenId) {
        createAuthorizationUseCase.create(CreateAuthorizationCommand
                .successfulPasswordAuthorization(passwordAuthorizationCommand.getUsername(), passwordAuthorizationCommand.getPassword(), accessTokenId, refreshTokenId));
    }

    /**
     * Успешная авторизация
     * @param credentialEntity
     */
    private void successAuthentication(CredentialEntity credentialEntity) {
        credentialEntity.clearFails();
        credentialRepository.save(credentialEntity);
    }

    /**
     * Провальная аутентификация
     * @param credentialEntity
     */
    private void failAuthentication(CredentialEntity credentialEntity) {
        credentialEntity.addFail();
        credentialRepository.save(credentialEntity);
    }

    /**
     * Валидация входящих данных
     * @param passwordAuthorizationCommand
     * @throws UsernameIsRequiredException
     * @throws PasswordIsRequiredException
     */
    private void validate(PasswordAuthorizationCommand passwordAuthorizationCommand) throws UsernameIsRequiredException, PasswordIsRequiredException {
        if (!StringUtils.hasText(passwordAuthorizationCommand.getUsername()))
            throw new UsernameIsRequiredException();

        if (!StringUtils.hasText(passwordAuthorizationCommand.getPassword()))
            throw new PasswordIsRequiredException();
    }
}

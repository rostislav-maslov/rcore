package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
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

/**
 * Авторизация по логину-паролю
 */
@RequiredArgsConstructor
public class PasswordAuthorizationUseCase {

    private final CreateAuthorizationUseCase createAuthorizationUseCase;
    private final CredentialRepository credentialRepository;
    private final PasswordCryptographer passwordCryptographer;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;

    public TokenPair login(PasswordAuthorizationCommand passwordAuthorizationCommand) throws UsernameIsRequiredException, PasswordIsRequiredException, BadCredentialsException, CredentialIsBlockedException {
        validate(passwordAuthorizationCommand);

        //Находим учетные данные по username
        CredentialEntity credentialEntity = credentialRepository.findByUsername(passwordAuthorizationCommand.getUsername())
                .orElseThrow(() -> {
                    createFailedAuthentication(passwordAuthorizationCommand.getUsername());
                    return new BadCredentialsException();
                });

        //Проверка правильности переданного пароля
        if (passwordCryptographer.validate(passwordAuthorizationCommand.getPassword(), credentialEntity.getPassword(), credentialEntity.getId()) == false) {
            createFailedAuthentication(passwordAuthorizationCommand.getUsername());
            throw new BadCredentialsException();
        }

        //Проверка заблокированного пользователя, то 
        if (credentialEntity.isBlocked()) {
            createFailedAuthentication(passwordAuthorizationCommand.getUsername());
            throw new CredentialIsBlockedException();
        }

        //создаем токены авторизации для учетных данных
        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.create(credentialEntity);
        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.create(credentialEntity, refreshTokenEntity);

        //Успешная авторизация
        successAuthentication(credentialEntity, accessTokenEntity, refreshTokenEntity);

        return TokenPair.builder()
                .accessToken(accessTokenEntity)
                .refreshToken(refreshTokenEntity)
                .build();
    }

    private void createSuccessfulAuthentication(CredentialEntity credentialEntity, String accessTokenId, String refreshTokenId) {
        createAuthorizationUseCase.create(CreateAuthorizationCommand
                .successfulPasswordAuthorization(credentialEntity, accessTokenId, refreshTokenId));
    }

    private void createFailedAuthentication(String username) {
        createAuthorizationUseCase.create(CreateAuthorizationCommand.failedPasswordAuthorization(username));
    }


    /**
     * Успешная авторизация
     *
     * @param credentialEntity
     */
    private void successAuthentication(CredentialEntity credentialEntity, AccessTokenEntity accessTokenEntity, RefreshTokenEntity refreshTokenEntity) {
        //Создаем успешную авторизацию
        createSuccessfulAuthentication(credentialEntity, accessTokenEntity.getId(), refreshTokenEntity.getId());
    }

    /**
     * Провальная авторизация
     */
    private void failAuthentication(String username) {
        createFailedAuthentication(username);
    }

    /**
     * Валидация входящих данных
     *
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

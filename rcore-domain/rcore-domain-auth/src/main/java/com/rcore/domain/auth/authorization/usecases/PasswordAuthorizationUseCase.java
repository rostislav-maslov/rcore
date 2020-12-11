package com.rcore.domain.auth.authorization.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
import com.rcore.domain.auth.authorization.exceptions.PasswordIsRequiredException;
import com.rcore.domain.auth.authorization.exceptions.UsernameIsRequiredException;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialIsBlockedException;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.entity.TokenPair;
import com.rcore.domain.auth.token.usecases.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecases.CreateRefreshTokenUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class PasswordAuthorizationUseCase extends UseCase<PasswordAuthorizationUseCase.InputValues, PasswordAuthorizationUseCase.OutputValues> {

    private final CreateAuthorizationUseCase createAuthorizationUseCase;
    private final CredentialRepository credentialRepository;
    private final PasswordCryptographer passwordCryptographer;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;

    @Override
    public OutputValues execute(InputValues inputValues) {
        validate(inputValues);

        //Находим учетные данные по username
        CredentialEntity credentialEntity = credentialRepository.findByUsername(inputValues.getUsername())
                .orElseThrow(() -> {
                    createFailedAuthentication(inputValues.getUsername());
                    return new BadCredentialsException(inputValues.getUsername());
                });

        //Проверка правильности переданного пароля
        if (passwordCryptographer.validate(inputValues.getPassword(), credentialEntity.getPassword(), credentialEntity.getId()) == false) {
            createFailedAuthentication(inputValues.getUsername());
            throw new BadCredentialsException();
        }

        //Проверка заблокированного пользователя, то
        if (credentialEntity.isBlocked()) {
            createFailedAuthentication(inputValues.getUsername());
            throw new CredentialIsBlockedException(credentialEntity.getId());
        }

        //создаем токены авторизации для учетных данных
        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.execute(new CreateRefreshTokenUseCase.InputValues(credentialEntity)).getRefreshTokenEntity();
        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.execute(CreateAccessTokenUseCase.InputValues.of(credentialEntity, refreshTokenEntity)).getAccessTokenEntity();

        //Успешная авторизация
        successAuthentication(credentialEntity, accessTokenEntity, refreshTokenEntity);

        return new OutputValues(
                TokenPair.builder()
                        .accessToken(accessTokenEntity)
                        .refreshToken(refreshTokenEntity)
                        .build()
        );
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String username;
        private final String password;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final TokenPair tokenPair;
    }

    private void createSuccessfulAuthentication(CredentialEntity credentialEntity, String accessTokenId, String refreshTokenId) {
        createAuthorizationUseCase.execute(CreateAuthorizationUseCase.InputValues
                .successfulPasswordAuthorization(credentialEntity, accessTokenId, refreshTokenId));
    }

    private void createFailedAuthentication(String username) {
        createAuthorizationUseCase.execute(CreateAuthorizationUseCase.InputValues.failedPasswordAuthorization(username));
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
     * Валидация входящих данных
     *
     * @param inputValues
     */
    private void validate(InputValues inputValues) {
        if (!StringUtils.hasText(inputValues.getUsername()))
            throw new UsernameIsRequiredException();

        if (!StringUtils.hasText(inputValues.getPassword()))
            throw new PasswordIsRequiredException();
    }

}

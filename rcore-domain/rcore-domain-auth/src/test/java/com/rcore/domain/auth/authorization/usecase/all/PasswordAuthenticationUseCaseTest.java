package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
import com.rcore.domain.auth.authorization.exceptions.PasswordIsRequiredException;
import com.rcore.domain.auth.authorization.exceptions.UsernameIsRequiredException;
import com.rcore.domain.auth.authorization.usecases.PasswordAuthorizationUseCase;
import com.rcore.domain.auth.credential.exceptions.CredentialIsBlockedException;
import com.rcore.domain.auth.token.entity.TokenPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PasswordAuthenticationUseCaseTest extends PasswordAuthenticationUseCaseTestInfrastructure {

    /**
     * Тестирование удачной авторизации
     *
     *
     * @throws UsernameIsRequiredException
     * @throws BadCredentialsException
     * @throws CredentialIsBlockedException
     * @throws PasswordIsRequiredException
     */
    @Test
    void testSuccessfulLogin() throws UsernameIsRequiredException, BadCredentialsException, CredentialIsBlockedException, PasswordIsRequiredException {
        TokenPair tokenPair = authorizationConfig.passwordAuthorizationUseCase()
                .execute(PasswordAuthorizationUseCase.InputValues
                        .of(username, password))
                .getTokenPair();

        Assertions.assertNotNull(tokenPair);
        Assertions.assertNotNull(tokenPair.getAccessToken());
        Assertions.assertNotNull(tokenPair.getRefreshToken());
    }

    /**
     * Провальная авторизация с передачей пустого username
     */
    @Test
    void testFailedLoginWithEmptyUsername() {
        Assertions.assertThrows(UsernameIsRequiredException.class, () -> authorizationConfig.passwordAuthorizationUseCase()
                .execute(PasswordAuthorizationUseCase.InputValues
                        .of(null, password)));
    }

    /**
     * Провальная авторизация с передачей пустого password
     */
    @Test
    void testFailedLoginWithEmptyPassword() {
        Assertions.assertThrows(PasswordIsRequiredException.class, () -> authorizationConfig.passwordAuthorizationUseCase()
                .execute(PasswordAuthorizationUseCase.InputValues
                        .of(username, null)));
    }

    /**
     * Провальная авторизация с передачей неправильного username
     */
    @Test
    void testFailedLoginWithInvalidUsername() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.passwordAuthorizationUseCase()
                .execute(PasswordAuthorizationUseCase.InputValues
                        .of(username + "a", password)));
    }

    /**
     * Провальная авторизация с передачей неправильного password
     */
    @Test
    void testFailedLoginWithInvalidPassword() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.passwordAuthorizationUseCase()
                .execute(PasswordAuthorizationUseCase.InputValues
                        .of(username, password + 1)));
    }
}
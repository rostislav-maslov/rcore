package com.rcore.domain.auth.auth.usecase.all;

import com.rcore.domain.auth.auth.usecase.AuthorizationUseCaseTestInfrastructure;
import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
import com.rcore.domain.auth.authorization.exceptions.InvalidUsernameException;
import com.rcore.domain.auth.authorization.exceptions.PasswordIsRequiredException;
import com.rcore.domain.auth.authorization.exceptions.UsernameIsRequiredException;
import com.rcore.domain.auth.authorization.usecase.all.commands.PasswordAuthorizationCommand;
import com.rcore.domain.auth.credential.exceptions.CredentialIsBlockedException;
import com.rcore.domain.auth.token.entity.TokenPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PasswordAuthenticationUseCaseTest extends PasswordAuthenticationUseCaseTestInfrastructure {

    /**
     * Тестирование удачной авторизации
     *
     * @throws InvalidUsernameException
     * @throws UsernameIsRequiredException
     * @throws BadCredentialsException
     * @throws CredentialIsBlockedException
     * @throws PasswordIsRequiredException
     */
    @Test
    void testSuccessfulLogin() throws InvalidUsernameException, UsernameIsRequiredException, BadCredentialsException, CredentialIsBlockedException, PasswordIsRequiredException {
        TokenPair tokenPair = authorizationConfig.all.passwordAuthorizationUseCase()
                .login(PasswordAuthorizationCommand.builder()
                        .username(username)
                        .password(password)
                        .build());

        Assertions.assertNotNull(tokenPair);
        Assertions.assertNotNull(tokenPair.getAccessToken());
        Assertions.assertNotNull(tokenPair.getRefreshToken());
    }

    /**
     * Провальная авторизация с передачей пустого username
     */
    @Test
    void testFailedLoginWithEmptyUsername() {
        Assertions.assertThrows(UsernameIsRequiredException.class, () -> authorizationConfig.all.passwordAuthorizationUseCase()
                .login(PasswordAuthorizationCommand.builder()
                        .password(password)
                        .build()));
    }

    /**
     * Провальная авторизация с передачей пустого password
     */
    @Test
    void testFailedLoginWithEmptyPassword() {
        Assertions.assertThrows(PasswordIsRequiredException.class, () -> authorizationConfig.all.passwordAuthorizationUseCase()
                .login(PasswordAuthorizationCommand.builder()
                        .username(username)
                        .build()));
    }

    /**
     * Провальная авторизация с передачей неправильного username
     */
    @Test
    void testFailedLoginWithInvalidUsername() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.all.passwordAuthorizationUseCase()
                .login(PasswordAuthorizationCommand.builder()
                        .username(username + "1")
                        .password(password)
                        .build()));
    }

    /**
     * Провальная авторизация с передачей неправильного password
     */
    @Test
    void testFailedLoginWithInvalidPassword() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.all.passwordAuthorizationUseCase()
                .login(PasswordAuthorizationCommand.builder()
                        .username(username)
                        .password(password + "1")
                        .build()));
    }
}
package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.exceptions.*;
import com.rcore.domain.auth.authorization.usecase.all.commands.ConfirmTwoFactorAuthorizationCommand;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.exceptions.ConfirmationCodeIsExpiredException;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.token.entity.TokenPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class ConfirmTwoFactorAuthorizationUseCaseTest extends ConfirmTwoFactorAuthorizationUseCeseTestInfrastructure {

    /**
     * Успешное подтверждение кода
     *
     * @throws AuthorizationNotFoundException
     * @throws InvalidAuthorizationStatusException
     * @throws SendingTypeIsRequiredException
     * @throws AddressIsRequiredException
     * @throws CredentialNotFoundException
     * @throws ConfirmationCodeIsRequiredException
     * @throws BadCredentialsException
     */
    @Test
    void testSuccessfulConfirmTwoFactorAuthorization() throws AuthorizationNotFoundException, InvalidAuthorizationStatusException, SendingTypeIsRequiredException, AddressIsRequiredException, CredentialNotFoundException, ConfirmationCodeIsRequiredException, BadCredentialsException, ConfirmationCodeIsExpiredException {
        TokenPair tokenPair = authorizationConfig.all.confirmTwoFactorAuthorizationUseCase()
                .confirm(ConfirmTwoFactorAuthorizationCommand.builder()
                        .address(phone)
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        .code(code)
                        .build());

        Assertions.assertNotNull(tokenPair);
        Assertions.assertNotNull(tokenPair.getAccessToken());
        Assertions.assertNotNull(tokenPair.getRefreshToken());
    }

    /**
     * Провальное подтверждение кода при отклоненной авторизации
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithInvalidAuthorizationStatusException() {
        Mockito.when(authorizationRepository.findById(anyString()))
                .then(a -> Optional.of(rejectedAuthorization));

        Assertions.assertThrows(InvalidAuthorizationStatusException.class, () -> authorizationConfig.all.confirmTwoFactorAuthorizationUseCase()
                .confirm(ConfirmTwoFactorAuthorizationCommand.builder()
                        .address(phone)
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        .code(code)
                        .build()));
    }

    /**
     * Провальное подтверждение при передаче неверных данных
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithBadCredentialException() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.all.confirmTwoFactorAuthorizationUseCase()
                .confirm(ConfirmTwoFactorAuthorizationCommand.builder()
                        .address(phone + 1)
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        .code(code)
                        .build()));
    }

    /**
     * Провальное подтверждение кода из-за отсутствующего address
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithAddressIsRequiredException() {
        Assertions.assertThrows(AddressIsRequiredException.class, () -> authorizationConfig.all.confirmTwoFactorAuthorizationUseCase()
                .confirm(ConfirmTwoFactorAuthorizationCommand.builder()
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        .code(code)
                        .build()));
    }

    /**
     * Провальное подтверждение кода из-за отсутствующего code
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithConfirmedCodeIsRequiredException() {
        Assertions.assertThrows(ConfirmationCodeIsRequiredException.class, () -> authorizationConfig.all.confirmTwoFactorAuthorizationUseCase()
                .confirm(ConfirmTwoFactorAuthorizationCommand.builder()
                        .address(phone)
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        .build()));
    }

    /**
     * Провальное подтверждение кода из-за отсутствующего sendingType
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithSendingTypeIsRequiredException() {
        Assertions.assertThrows(SendingTypeIsRequiredException.class, () -> authorizationConfig.all.confirmTwoFactorAuthorizationUseCase()
                .confirm(ConfirmTwoFactorAuthorizationCommand.builder()
                        .address(phone)
                        .code(code)
                        .build()));
    }
}
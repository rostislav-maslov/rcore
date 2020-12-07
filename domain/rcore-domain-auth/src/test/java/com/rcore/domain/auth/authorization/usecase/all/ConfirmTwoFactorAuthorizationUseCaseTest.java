package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.exceptions.*;
import com.rcore.domain.auth.authorization.usecases.ConfirmTwoFactorAuthorizationUseCase;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.exceptions.ConfirmationCodeIsExpiredException;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.token.entity.TokenPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

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
        TokenPair tokenPair = authorizationConfig.confirmTwoFactorAuthorizationUseCase()
                .execute(ConfirmTwoFactorAuthorizationUseCase.InputValues.of(
                        phone,
                        ConfirmationCodeEntity.Recipient.SendingType.SMS,
                        code))
                .getTokenPair();

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

        Assertions.assertThrows(InvalidAuthorizationStatusException.class, () -> authorizationConfig.confirmTwoFactorAuthorizationUseCase()
                .execute(ConfirmTwoFactorAuthorizationUseCase.InputValues.of(
                        phone,
                        ConfirmationCodeEntity.Recipient.SendingType.SMS,
                        code)));
    }

    /**
     * Провальное подтверждение при передаче неверных данных
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithBadCredentialException() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.confirmTwoFactorAuthorizationUseCase()
                .execute(ConfirmTwoFactorAuthorizationUseCase.InputValues.of(
                        phone + 1,
                        ConfirmationCodeEntity.Recipient.SendingType.SMS,
                        code)));
    }

    /**
     * Провальное подтверждение кода из-за отсутствующего address
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithAddressIsRequiredException() {
        Assertions.assertThrows(AddressIsRequiredException.class, () -> authorizationConfig.confirmTwoFactorAuthorizationUseCase()
                .execute(ConfirmTwoFactorAuthorizationUseCase.InputValues.of(
                        null,
                        ConfirmationCodeEntity.Recipient.SendingType.SMS,
                        code)));
    }

    /**
     * Провальное подтверждение кода из-за отсутствующего code
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithConfirmedCodeIsRequiredException() {
        Assertions.assertThrows(ConfirmationCodeIsRequiredException.class, () -> authorizationConfig.confirmTwoFactorAuthorizationUseCase()
                .execute(ConfirmTwoFactorAuthorizationUseCase.InputValues.of(
                        phone,
                        ConfirmationCodeEntity.Recipient.SendingType.SMS,
                        null)));
    }

    /**
     * Провальное подтверждение кода из-за отсутствующего sendingType
     */
    @Test
    void testFailedConfirmTwoFactorAuthorizationWithSendingTypeIsRequiredException() {
        Assertions.assertThrows(SendingTypeIsRequiredException.class, () -> authorizationConfig.confirmTwoFactorAuthorizationUseCase()
                .execute(ConfirmTwoFactorAuthorizationUseCase.InputValues.of(
                        phone,
                        null,
                        code)));
    }
}
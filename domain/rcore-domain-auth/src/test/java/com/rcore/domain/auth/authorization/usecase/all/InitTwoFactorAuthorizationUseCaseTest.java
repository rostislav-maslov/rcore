package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.exceptions.AddressIsRequiredException;
import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
import com.rcore.domain.auth.authorization.exceptions.SendingTypeIsRequiredException;
import com.rcore.domain.auth.authorization.usecase.all.commands.InitTwoFactorAuthorizationCommand;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.exceptions.ExistNotConfirmedCodeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class InitTwoFactorAuthorizationUseCaseTest extends InitTwoFactorAuthorizationUseCaseTestInfrastructure {

    /**
     * Успешная инициализация двухвакторной авторизации через sms
     *
     * @throws BadCredentialsException
     * @throws SendingTypeIsRequiredException
     * @throws AddressIsRequiredException
     * @throws ExistNotConfirmedCodeException
     */
    @Test
    void testSuccessfulInitTwoFactorAuthorizationViaSmsCode() throws BadCredentialsException, SendingTypeIsRequiredException, AddressIsRequiredException, ExistNotConfirmedCodeException {
        authorizationConfig.all.initTwoFactorAuthorizationUseCase()
                .init(InitTwoFactorAuthorizationCommand.builder()
                        .address(phone)
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        .build());
    }

    /**
     * Успешная инициализация двухфакторной авторизации через email
     *
     * @throws BadCredentialsException
     * @throws SendingTypeIsRequiredException
     * @throws AddressIsRequiredException
     * @throws ExistNotConfirmedCodeException
     */
    @Test
    void testSuccessfulInitTwoFactorAuthorizationViaEmailCode() throws BadCredentialsException, SendingTypeIsRequiredException, AddressIsRequiredException, ExistNotConfirmedCodeException {
        authorizationConfig.all.initTwoFactorAuthorizationUseCase()
                .init(InitTwoFactorAuthorizationCommand.builder()
                        .address(email)
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.EMAIL)
                        .build());
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче невалидных данных
     */
    @Test
    void testFailedInitTwoFactorAuthorizationViaSmsCodeWithBadCredentialException() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.all.initTwoFactorAuthorizationUseCase()
                .init(InitTwoFactorAuthorizationCommand.builder()
                        .address(phone + "1")
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        .build()));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче невалидных данных
     */
    @Test
    void testFailedInitTwoFactorAuthorizationViaEmailCodeWithBadCredentialException() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.all.initTwoFactorAuthorizationUseCase()
                .init(InitTwoFactorAuthorizationCommand.builder()
                        .address(email + "a")
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.EMAIL)
                        .build()));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче пустого address
     */
    @Test
    void testFailedInitTwoFactorAuthorizationViaEmailCodeWithAddressIsRequiredException() {
        Assertions.assertThrows(AddressIsRequiredException.class, () -> authorizationConfig.all.initTwoFactorAuthorizationUseCase()
                .init(InitTwoFactorAuthorizationCommand.builder()
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.EMAIL)
                        .build()));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче пустого sendingType
     */
    @Test
    void testFailedInitTwoFactorAuthorizationViaEmailCodeWithSendingTypeIsRequiredException() {
        Assertions.assertThrows(SendingTypeIsRequiredException.class, () -> authorizationConfig.all.initTwoFactorAuthorizationUseCase()
                .init(InitTwoFactorAuthorizationCommand.builder()
                        .address(email)
                        .build()));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче пустого sendingType
     */
    @Test
    void testFailedInitTwoFactorAuthorizationWithExistNotConfirmedCodeException() {
        Mockito.when(confirmationCodeRepository.existNotConfirmedCode(any()))
                .then(a -> true);

        Assertions.assertThrows(ExistNotConfirmedCodeException.class, () -> authorizationConfig.all.initTwoFactorAuthorizationUseCase()
                .init(InitTwoFactorAuthorizationCommand.builder()
                        .address(email)
                        .sendingType(ConfirmationCodeEntity.Recipient.SendingType.EMAIL)
                        .build()));
    }
}
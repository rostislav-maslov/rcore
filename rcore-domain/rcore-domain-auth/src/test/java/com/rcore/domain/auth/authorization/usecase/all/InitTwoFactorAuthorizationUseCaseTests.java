package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.exceptions.AddressIsRequiredException;
import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
import com.rcore.domain.auth.authorization.exceptions.SendingTypeIsRequiredException;
import com.rcore.domain.auth.authorization.usecases.InitTwoFactorAuthorizationUseCase;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.exceptions.ExistNotConfirmedCodeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

class InitTwoFactorAuthorizationUseCaseTests extends InitTwoFactorAuthorizationUseCaseTestInfrastructure {

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
        authorizationConfig.initTwoFactorAuthorizationUseCase()
                .execute(InitTwoFactorAuthorizationUseCase.InputValues
                        .of(phone, ConfirmationCodeEntity.Recipient.SendingType.SMS, 60l));
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
        authorizationConfig.initTwoFactorAuthorizationUseCase()
                .execute(InitTwoFactorAuthorizationUseCase.InputValues
                        .of(email, ConfirmationCodeEntity.Recipient.SendingType.EMAIL, 60l));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче невалидных данных
     */
    @Test
    void testFailedInitTwoFactorAuthorizationViaSmsCodeWithBadCredentialException() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.initTwoFactorAuthorizationUseCase()
                .execute(InitTwoFactorAuthorizationUseCase.InputValues
                        .of(phone + 1, ConfirmationCodeEntity.Recipient.SendingType.SMS, 60l)));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче невалидных данных
     */
    @Test
    void testFailedInitTwoFactorAuthorizationViaEmailCodeWithBadCredentialException() {
        Assertions.assertThrows(BadCredentialsException.class, () -> authorizationConfig.initTwoFactorAuthorizationUseCase()
                .execute(InitTwoFactorAuthorizationUseCase.InputValues
                        .of(email + "a", ConfirmationCodeEntity.Recipient.SendingType.EMAIL, 60l)));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче пустого address
     */
    @Test
    void testFailedInitTwoFactorAuthorizationViaEmailCodeWithAddressIsRequiredException() {
        Assertions.assertThrows(AddressIsRequiredException.class, () -> authorizationConfig.initTwoFactorAuthorizationUseCase()
                .execute(InitTwoFactorAuthorizationUseCase.InputValues
                        .of(null, ConfirmationCodeEntity.Recipient.SendingType.SMS, 60l)));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче пустого sendingType
     */
    @Test
    void testFailedInitTwoFactorAuthorizationViaEmailCodeWithSendingTypeIsRequiredException() {
        Assertions.assertThrows(SendingTypeIsRequiredException.class, () -> authorizationConfig.initTwoFactorAuthorizationUseCase()
                .execute(InitTwoFactorAuthorizationUseCase.InputValues
                        .of(phone, null, 60l)));
    }

    /**
     * Провальная инициализация двухфакторной авторизации при передаче пустого sendingType
     */
    @Test
    void testFailedInitTwoFactorAuthorizationWithExistNotConfirmedCodeException() {
        Mockito.when(confirmationCodeRepository.existNotConfirmedCode(any()))
                .then(a -> true);

        Assertions.assertThrows(ExistNotConfirmedCodeException.class, () -> authorizationConfig.initTwoFactorAuthorizationUseCase()
                .execute(InitTwoFactorAuthorizationUseCase.InputValues
                        .of(email, ConfirmationCodeEntity.Recipient.SendingType.EMAIL, 60l)));
    }
}
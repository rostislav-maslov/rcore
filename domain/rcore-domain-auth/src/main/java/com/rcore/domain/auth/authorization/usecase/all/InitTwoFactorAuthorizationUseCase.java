package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
import com.rcore.domain.auth.authorization.exceptions.AddressIsRequiredException;
import com.rcore.domain.auth.authorization.exceptions.SendingTypeIsRequiredException;
import com.rcore.domain.auth.authorization.usecase.all.commands.CreateAuthorizationCommand;
import com.rcore.domain.auth.authorization.usecase.all.commands.InitTwoFactorAuthorizationCommand;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.exceptions.ExistNotConfirmedCodeException;
import com.rcore.domain.auth.confirmationCode.usecase.all.CreateConfirmationCodeUseCase;
import com.rcore.domain.auth.confirmationCode.usecase.all.commands.CreateConfirmationCodeCommand;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import lombok.RequiredArgsConstructor;

/**
 * Инициализация двухфакторной авторизации
 */
@RequiredArgsConstructor
public class InitTwoFactorAuthorizationUseCase {

    private final CreateAuthorizationUseCase createAuthorizationUseCase;
    private final CredentialRepository credentialRepository;
    private final CreateConfirmationCodeUseCase createConfirmationCodeUseCase;

    public void init(InitTwoFactorAuthorizationCommand initTwoFactorAuthorizationCommand) throws AddressIsRequiredException, BadCredentialsException, ExistNotConfirmedCodeException, SendingTypeIsRequiredException {
        validate(initTwoFactorAuthorizationCommand);

        CredentialEntity credentialEntity;

        //Если тип отправки сообщения SMS, то ищем по номеру телефона, иначе по email
        if (initTwoFactorAuthorizationCommand.getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS))
            credentialEntity = credentialRepository.findByPhone(initTwoFactorAuthorizationCommand.getAddress())
                    .orElseThrow(() -> {
                        createFailedAuthorization(initTwoFactorAuthorizationCommand);
                        return new BadCredentialsException();
                    });
        else
            credentialEntity = credentialRepository.findByEmail(initTwoFactorAuthorizationCommand.getAddress())
                    .orElseThrow(() -> {
                        createFailedAuthorization(initTwoFactorAuthorizationCommand);
                        return new BadCredentialsException();
                    });

        //Создачем авторизацию
        AuthorizationEntity authorizationEntity = createSuccessfulAuthorization(credentialEntity, initTwoFactorAuthorizationCommand);
        //Создаем код для подтверждения
        createConfirmationCodeUseCase.create(CreateConfirmationCodeCommand.of(
                authorizationEntity.getId(),
                ConfirmationCodeEntity.Recipient.of(credentialEntity.getId(), initTwoFactorAuthorizationCommand.getAddress(), initTwoFactorAuthorizationCommand.getSendingType()),
                60l)
        );
    }

    private void validate(InitTwoFactorAuthorizationCommand initTwoFactorAuthorizationCommand) throws AddressIsRequiredException, SendingTypeIsRequiredException {
        if (!StringUtils.hasText(initTwoFactorAuthorizationCommand.getAddress()))
            throw new AddressIsRequiredException();

        if (initTwoFactorAuthorizationCommand.getSendingType() == null)
            throw new SendingTypeIsRequiredException();
    }

    private AuthorizationEntity createSuccessfulAuthorization(CredentialEntity credentialEntity, InitTwoFactorAuthorizationCommand initTwoFactorAuthorizationCommand) {
        return createAuthorizationUseCase.create(CreateAuthorizationCommand
                .successfulInit2FAuthorization(credentialEntity, ConfirmationCodeEntity.Recipient.of(credentialEntity.getId(), initTwoFactorAuthorizationCommand.getAddress(), initTwoFactorAuthorizationCommand.getSendingType())));
    }

    private void createFailedAuthorization(InitTwoFactorAuthorizationCommand initTwoFactorAuthorizationCommand) {
        createAuthorizationUseCase.create(CreateAuthorizationCommand
                .failedInit2FAuthorization(ConfirmationCodeEntity.Recipient.of(initTwoFactorAuthorizationCommand.getAddress(), initTwoFactorAuthorizationCommand.getSendingType())));
    }

}

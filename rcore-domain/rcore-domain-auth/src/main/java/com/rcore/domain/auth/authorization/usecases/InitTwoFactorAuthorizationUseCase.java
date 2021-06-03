package com.rcore.domain.auth.authorization.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.exceptions.AddressIsRequiredException;
import com.rcore.domain.auth.authorization.exceptions.BadCredentialsException;
import com.rcore.domain.auth.authorization.exceptions.SendingTypeIsRequiredException;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.usecases.CreateConfirmationCodeUseCase;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.usecases.FindCredentialByEmailUseCase;
import com.rcore.domain.auth.credential.usecases.FindCredentialByPhoneUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingleInput;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class InitTwoFactorAuthorizationUseCase extends UseCase<InitTwoFactorAuthorizationUseCase.InputValues, InitTwoFactorAuthorizationUseCase.OutputValues> {

    private final CreateAuthorizationUseCase createAuthorizationUseCase;
    private final CreateConfirmationCodeUseCase createConfirmationCodeUseCase;
    private final FindCredentialByEmailUseCase findCredentialByEmailUseCase;
    private final FindCredentialByPhoneUseCase findCredentialByPhoneUseCase;
    private final FindPendingAuthorizationByAddressUseCase findPendingAuthorizationByAddressUseCase;

    @Override
    public OutputValues execute(InputValues inputValues) {
        validate(inputValues);

        CredentialEntity credentialEntity;

        //Если тип отправки сообщения SMS, то ищем по номеру телефона, иначе по email
        if (inputValues.getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS))
            credentialEntity = findCredentialByPhoneUseCase.execute(FindCredentialByPhoneUseCase.InputValues.of(inputValues.getAddress()))
                    .getCredentialEntity()
                    .orElseThrow(() -> {
                        createFailedAuthorization(inputValues);
                        return new BadCredentialsException(inputValues.getAddress());
                    });
        else
            credentialEntity = findCredentialByEmailUseCase.execute(FindCredentialByEmailUseCase.InputValues.of(inputValues.getAddress()))
                    .getEntity()
                    .orElseThrow(() -> {
                        createFailedAuthorization(inputValues);
                        return new BadCredentialsException(inputValues.getAddress());
                    });

        //Создачем авторизацию
        AuthorizationEntity authorizationEntity = findPendingAuthorizationByAddressUseCase.execute(SingleInput.of(inputValues.getAddress()))
                .getValue()
                .orElseGet(() -> createSuccessfulAuthorization(credentialEntity, inputValues));
        //Создаем код для подтверждения
        var confirmationCode = createConfirmationCodeUseCase.execute(new CreateConfirmationCodeUseCase.InputValues(
                authorizationEntity.getId(),
                ConfirmationCodeEntity.Recipient.of(credentialEntity.getId(), inputValues.getAddress(), inputValues.getSendingType()),
                inputValues.getTtl())
        );

        return new OutputValues(confirmationCode.getConfirmationCodeEntity());
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        String address;
        ConfirmationCodeEntity.Recipient.SendingType sendingType;
        Long ttl;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        ConfirmationCodeEntity confirmationCode;
    }

    private void validate(InputValues inputValues) {
        if (!StringUtils.hasText(inputValues.getAddress()))
            throw new AddressIsRequiredException();

        if (inputValues.getSendingType() == null)
            throw new SendingTypeIsRequiredException();
    }

    private AuthorizationEntity createSuccessfulAuthorization(CredentialEntity credentialEntity, InputValues inputValues) {
        return createAuthorizationUseCase.execute(CreateAuthorizationUseCase.InputValues
                .successfulInit2FAuthorization(ConfirmationCodeEntity.Recipient.of(credentialEntity.getId(), inputValues.getAddress(), inputValues.getSendingType())))
                .getEntity();
    }

    private void createFailedAuthorization(InputValues inputValues) {
        createAuthorizationUseCase.execute(CreateAuthorizationUseCase.InputValues
                .failedInit2FAuthorization(ConfirmationCodeEntity.Recipient.of(inputValues.getAddress(), inputValues.getSendingType())));
    }

}

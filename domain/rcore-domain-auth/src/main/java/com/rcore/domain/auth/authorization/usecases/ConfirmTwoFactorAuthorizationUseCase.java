package com.rcore.domain.auth.authorization.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.exceptions.*;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.exceptions.ConfirmationCodeIsExpiredException;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.credential.usecases.GetCredentialByIdUseCase;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.entity.TokenPair;
import com.rcore.domain.auth.token.usecases.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecases.CreateRefreshTokenUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class ConfirmTwoFactorAuthorizationUseCase extends UseCase<ConfirmTwoFactorAuthorizationUseCase.InputValues, ConfirmTwoFactorAuthorizationUseCase.OutputValues> {

    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final GetAuthorizationByIdUseCase getAuthorizationByIdUseCase;
    private final GetCredentialByIdUseCase getCredentialByIdUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final TransferAuthorizationToSuccessStatusUseCase transferAuthorizationToSuccessStatusUseCase;

    @Override
    public OutputValues execute(InputValues inputValues) {
        validate(inputValues);

        ConfirmationCodeEntity confirmationCodeEntity = confirmationCodeRepository.findNotConfirmedByAddressAndSendingTypeAndCode(inputValues.getAddress(), inputValues.getSendingType(), inputValues.getCode())
                .orElseThrow(BadCredentialsException::new);

        AuthorizationEntity authorizationEntity = getAuthorizationByIdUseCase.execute(GetAuthorizationByIdUseCase.InputValues.of(confirmationCodeEntity.getAuthorizationId()))
                .getAuthorizationEntity()
                .orElseThrow(() -> new AuthorizationNotFoundException(confirmationCodeEntity.getAuthorizationId()));

        CredentialEntity credentialEntity = getCredentialByIdUseCase.execute(GetCredentialByIdUseCase.InputValues.of(authorizationEntity.getCredentialId()))
                .getCredentialEntity()
                .orElseThrow(() -> new CredentialNotFoundException(authorizationEntity.getCredentialId()));

        if (confirmationCodeEntity.isExpired())
            throw new ConfirmationCodeIsExpiredException(confirmationCodeEntity.getCode(), confirmationCodeEntity.getExpiredAt());

        //Если авторизация НЕ в статусе ожидания подтверждения
        if (!authorizationEntity.isPendingConfirm())
            throw new InvalidAuthorizationStatusException(authorizationEntity.getStatus().name());

        //создаем токены авторизации для учетных данных
        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.execute(new CreateRefreshTokenUseCase.InputValues(credentialEntity))
                .getRefreshTokenEntity();
        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.execute(new CreateAccessTokenUseCase.InputValues(credentialEntity, refreshTokenEntity))
                .getAccessTokenEntity();

        successfulConfirmation(confirmationCodeEntity, authorizationEntity, accessTokenEntity, refreshTokenEntity);

        return new OutputValues(
                TokenPair.builder()
                        .accessToken(accessTokenEntity)
                        .refreshToken(refreshTokenEntity)
                        .build()
        );
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String address;
        private final ConfirmationCodeEntity.Recipient.SendingType sendingType;
        private final String code;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final TokenPair tokenPair;
    }

    private void successfulConfirmation(ConfirmationCodeEntity confirmationCodeEntity, AuthorizationEntity authorizationEntity, AccessTokenEntity accessTokenEntity, RefreshTokenEntity refreshTokenEntity) throws AuthorizationNotFoundException {
        confirmationCodeEntity.setConfirmed();
        confirmationCodeRepository.save(confirmationCodeEntity);

        transferAuthorizationToSuccessStatusUseCase.execute(TransferAuthorizationToSuccessStatusUseCase.InputValues.of(
                accessTokenEntity.getId(),
                refreshTokenEntity.getId(),
                authorizationEntity.getId()
        ));
    }

    private void validate(InputValues inputValues) {
        if (!StringUtils.hasText(inputValues.getAddress()))
            throw new AddressIsRequiredException();

        if (inputValues.getSendingType() == null)
            throw new SendingTypeIsRequiredException();

        if (!StringUtils.hasText(inputValues.getCode()))
            throw new ConfirmationCodeIsRequiredException();
    }

}

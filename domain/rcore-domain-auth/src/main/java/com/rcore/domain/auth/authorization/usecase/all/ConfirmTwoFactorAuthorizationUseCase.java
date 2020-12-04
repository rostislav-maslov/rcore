package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.exceptions.*;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.authorization.usecase.all.commands.ConfirmTwoFactorAuthorizationCommand;
import com.rcore.domain.auth.authorization.usecase.all.commands.SuccessfulAuthorizationCommand;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.exceptions.ConfirmationCodeIsExpiredException;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.entity.TokenPair;
import com.rcore.domain.auth.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.auth.token.usecase.CreateRefreshTokenUseCase;
import lombok.RequiredArgsConstructor;

/**
 * Подтверждение кода авторизации при двухфакторной авторизации
 */
@RequiredArgsConstructor
public class ConfirmTwoFactorAuthorizationUseCase {

    private final AuthorizationRepository authorizationRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final CredentialRepository credentialRepository;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final SuccessfulAuthorizationUseCase successfulAuthorizationUseCase;

    public TokenPair confirm(ConfirmTwoFactorAuthorizationCommand confirmTwoFactorAuthorizationCommand) throws AddressIsRequiredException, ConfirmationCodeIsRequiredException, BadCredentialsException, AuthorizationNotFoundException, InvalidAuthorizationStatusException, CredentialNotFoundException, SendingTypeIsRequiredException, ConfirmationCodeIsExpiredException {
        validate(confirmTwoFactorAuthorizationCommand);

        ConfirmationCodeEntity confirmationCodeEntity = confirmationCodeRepository.findNotConfirmedByAddressAndSendingTypeAndCode(confirmTwoFactorAuthorizationCommand.getAddress(), confirmTwoFactorAuthorizationCommand.getSendingType(), confirmTwoFactorAuthorizationCommand.getCode())
                .orElseThrow(BadCredentialsException::new);

        AuthorizationEntity authorizationEntity = authorizationRepository.findById(confirmationCodeEntity.getAuthorizationId())
                .orElseThrow(AuthorizationNotFoundException::new);

        CredentialEntity credentialEntity = credentialRepository.findById(authorizationEntity.getCredentialId())
                .orElseThrow(CredentialNotFoundException::new);

        if (confirmationCodeEntity.isExpired())
            throw new ConfirmationCodeIsExpiredException();

        //Если авторизация НЕ в статусе ожидания подтверждения
        if (!authorizationEntity.isPendingConfirm())
            throw new InvalidAuthorizationStatusException();

        //создаем токены авторизации для учетных данных
        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.create(credentialEntity);
        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.create(credentialEntity, refreshTokenEntity);

        successfulConfirmation(confirmationCodeEntity, authorizationEntity, credentialEntity, accessTokenEntity, refreshTokenEntity);

        return TokenPair.builder()
                .accessToken(accessTokenEntity)
                .refreshToken(refreshTokenEntity)
                .build();
    }

    private void successfulConfirmation(ConfirmationCodeEntity confirmationCodeEntity, AuthorizationEntity authorizationEntity, CredentialEntity credentialEntity, AccessTokenEntity accessTokenEntity, RefreshTokenEntity refreshTokenEntity) throws AuthorizationNotFoundException {
        confirmationCodeEntity.setConfirmed();
        confirmationCodeRepository.save(confirmationCodeEntity);

        successfulAuthorizationUseCase.successAuthorization(SuccessfulAuthorizationCommand.builder()
                .authorizationId(authorizationEntity.getId())
                .accessTokeId(accessTokenEntity.getId())
                .refreshTokeId(refreshTokenEntity.getId())
                .build());
    }

    private void validate(ConfirmTwoFactorAuthorizationCommand confirmTwoFactorAuthorizationCommand) throws AddressIsRequiredException, ConfirmationCodeIsRequiredException, SendingTypeIsRequiredException {
        if (!StringUtils.hasText(confirmTwoFactorAuthorizationCommand.getAddress()))
            throw new AddressIsRequiredException();

        if (confirmTwoFactorAuthorizationCommand.getSendingType() == null)
            throw new SendingTypeIsRequiredException();

        if (!StringUtils.hasText(confirmTwoFactorAuthorizationCommand.getCode()))
            throw new ConfirmationCodeIsRequiredException();
    }
}

package com.rcore.domain.auth.authorization.usecase.all.commands;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class CreateAuthorizationCommand {
    private AuthorizationEntity.Type type;
    private String credentialId;
    private AuthorizationEntity.AuthorizationData authorizationData;
    private AuthorizationEntity.Status status = AuthorizationEntity.Status.NEW;
    private AuthorizationEntity.RejectionCause rejectionCause;
    private String accessTokenId;
    private String refreshTokenId;

    /**
     * Успешная авторизация через пароль
     *
     * @param credentialEntity
     * @param accessTokenId
     * @param refreshTokenId
     * @return
     */
    public static CreateAuthorizationCommand successfulPasswordAuthorization(CredentialEntity credentialEntity, String accessTokenId, String refreshTokenId) {
        return CreateAuthorizationCommand.builder()
                .type(AuthorizationEntity.Type.PASSWORD)
                .status(AuthorizationEntity.Status.SUCCESS)
                .accessTokenId(accessTokenId)
                .refreshTokenId(refreshTokenId)
                .authorizationData(AuthorizationEntity.AuthorizationData.passwordAuthenticationData(credentialEntity.getUsername()))
                .credentialId(credentialEntity.getId())
                .build();
    }

    public static CreateAuthorizationCommand failedPasswordAuthorization(String username) {
        return CreateAuthorizationCommand.builder()
                .type(AuthorizationEntity.Type.PASSWORD)
                .status(AuthorizationEntity.Status.REJECTED)
                .authorizationData(AuthorizationEntity.AuthorizationData.passwordAuthenticationData(username))
                .rejectionCause(AuthorizationEntity.RejectionCause.BAD_CREDENTIAL)
                .build();
    }

    public static CreateAuthorizationCommand successfulInit2FAuthorization(CredentialEntity credentialEntity, ConfirmationCodeEntity.Recipient recipient) {
        return CreateAuthorizationCommand.builder()
                .type(recipient.getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        ? AuthorizationEntity.Type.SMS_2FA
                        : AuthorizationEntity.Type.EMAIL_2FA)
                .status(AuthorizationEntity.Status.PENDING_CONFIRMATION)
                .authorizationData(recipient.getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        ? AuthorizationEntity.AuthorizationData.sms2FAuthenticationData(recipient.getAddress())
                        : AuthorizationEntity.AuthorizationData.email2FAuthenticationData(recipient.getAddress()))
                .build();
    }

    public static CreateAuthorizationCommand failedInit2FAuthorization(ConfirmationCodeEntity.Recipient recipient) {
        return CreateAuthorizationCommand.builder()
                .type(recipient.getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        ? AuthorizationEntity.Type.SMS_2FA
                        : AuthorizationEntity.Type.EMAIL_2FA)
                .status(AuthorizationEntity.Status.REJECTED)
                .authorizationData(recipient.getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                        ? AuthorizationEntity.AuthorizationData.sms2FAuthenticationData(recipient.getAddress())
                        : AuthorizationEntity.AuthorizationData.email2FAuthenticationData(recipient.getAddress()))
                .rejectionCause(AuthorizationEntity.RejectionCause.BAD_CREDENTIAL)
                .build();
    }
}

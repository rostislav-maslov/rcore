package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationIdGenerator;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.*;

public class CreateAuthorizationUseCase
        extends AbstractCreateUseCase<AuthorizationEntity, AuthorizationIdGenerator<?>, AuthorizationRepository, CreateAuthorizationUseCase.InputValues> {

    public CreateAuthorizationUseCase(AuthorizationRepository repository, AuthorizationIdGenerator<?> idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<AuthorizationEntity> execute(InputValues inputValues) {
        AuthorizationEntity authorizationEntity = new AuthorizationEntity();
        authorizationEntity.setId(idGenerator.generate());
        authorizationEntity.setCredentialId(inputValues.getCredentialId());
        authorizationEntity.setAuthorizationData(inputValues.getAuthorizationData());
        authorizationEntity.setType(inputValues.getType());
        authorizationEntity.setStatus(inputValues.getStatus());
        authorizationEntity.setRejectionCause(inputValues.getRejectionCause());
        authorizationEntity.setAccessTokenId(inputValues.getAccessTokenId());
        authorizationEntity.setRefreshTokenId(inputValues.getRefreshTokenId());
        return SingletonEntityOutputValues.of(repository.save(authorizationEntity));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
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
        public static InputValues successfulPasswordAuthorization(CredentialEntity credentialEntity, String accessTokenId, String refreshTokenId) {
            return InputValues.builder()
                    .credentialId(credentialEntity.getId())
                    .type(AuthorizationEntity.Type.PASSWORD)
                    .status(AuthorizationEntity.Status.SUCCESS)
                    .accessTokenId(accessTokenId)
                    .refreshTokenId(refreshTokenId)
                    .authorizationData(AuthorizationEntity.AuthorizationData.passwordAuthenticationData(credentialEntity.getUsername()))
                    .credentialId(credentialEntity.getId())
                    .build();
        }

        public static InputValues failedPasswordAuthorization(String username) {
            return InputValues.builder()
                    .type(AuthorizationEntity.Type.PASSWORD)
                    .status(AuthorizationEntity.Status.REJECTED)
                    .authorizationData(AuthorizationEntity.AuthorizationData.passwordAuthenticationData(username))
                    .rejectionCause(AuthorizationEntity.RejectionCause.BAD_CREDENTIAL)
                    .build();
        }

        public static InputValues successfulInit2FAuthorization(ConfirmationCodeEntity.Recipient recipient) {
            return InputValues.builder()
                    .credentialId(recipient.getCredentialId())
                    .type(recipient.getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                            ? AuthorizationEntity.Type.SMS_2FA
                            : AuthorizationEntity.Type.EMAIL_2FA)
                    .status(AuthorizationEntity.Status.PENDING_CONFIRMATION)
                    .authorizationData(recipient.getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS)
                            ? AuthorizationEntity.AuthorizationData.sms2FAuthenticationData(recipient.getAddress())
                            : AuthorizationEntity.AuthorizationData.email2FAuthenticationData(recipient.getAddress()))
                    .build();
        }

        public static InputValues failedInit2FAuthorization(ConfirmationCodeEntity.Recipient recipient) {
            return InputValues.builder()
                    .credentialId(recipient.getCredentialId())
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

}

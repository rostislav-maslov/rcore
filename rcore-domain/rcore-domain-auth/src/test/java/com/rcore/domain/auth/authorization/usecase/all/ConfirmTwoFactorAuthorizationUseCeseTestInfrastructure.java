package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.usecase.AuthorizationUseCaseTestInfrastructure;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class ConfirmTwoFactorAuthorizationUseCeseTestInfrastructure extends AuthorizationUseCaseTestInfrastructure {

    protected static final String authorizationId = UUID.randomUUID().toString();
    protected static final String confirmationCodeId = UUID.randomUUID().toString();
    protected static final String credentialId = UUID.randomUUID().toString();
    protected static final String email = "mail@mail";
    protected static final String phone = "79998887766";
    protected static final String code = UUID.randomUUID().toString();

    protected final CredentialEntity credential;

    protected final AuthorizationEntity pendingAuthorization = AuthorizationEntity.builder()
            .id(authorizationId)
            .status(AuthorizationEntity.Status.PENDING_CONFIRMATION)
            .credentialId(credentialId)
            .type(AuthorizationEntity.Type.SMS_2FA)
            .authorizationData(AuthorizationEntity.AuthorizationData.sms2FAuthenticationData(phone))
            .build();

    protected final AuthorizationEntity rejectedAuthorization = AuthorizationEntity.builder()
            .id(authorizationId)
            .status(AuthorizationEntity.Status.REJECTED)
            .credentialId(credentialId)
            .type(AuthorizationEntity.Type.SMS_2FA)
            .authorizationData(AuthorizationEntity.AuthorizationData.sms2FAuthenticationData(phone))
            .build();

    protected final ConfirmationCodeEntity confirmationCode = ConfirmationCodeEntity.builder()
            .id(confirmationCodeId)
            .authorizationId(authorizationId)
            .code(code)
            .confirmationStatus(ConfirmationCodeEntity.ConfirmationStatus.NOT_CONFIRMED)
            .sendingStatus(ConfirmationCodeEntity.SendingStatus.SENT)
            .sendingDate(LocalDateTime.now())
            .expiredAt(LocalDateTime.now()
                    .plusSeconds(60))
            .recipient(ConfirmationCodeEntity.Recipient.of(phone, credentialId, ConfirmationCodeEntity.Recipient.SendingType.SMS))
            .build();


    public ConfirmTwoFactorAuthorizationUseCeseTestInfrastructure() {
        initCredentialMocks();
        initConfirmedCodeMocks();
        initAuthorizationMocks();
        CredentialEntity defaultCredential = new CredentialEntity();
        defaultCredential.setId(credentialId);
        defaultCredential.setEmail(email);
        defaultCredential.setPhone(phone);
        defaultCredential.setStatus(CredentialEntity.Status.ACTIVE);
        this.credential = defaultCredential;
    }

    private void initCredentialMocks() {
        Mockito.when(credentialRepository.findByEmail(anyString()))
                .thenAnswer(a -> {
                    if (a.getArgument(0).toString().equals(email))
                        return Optional.of(credential);
                    else return Optional.empty();
                });

        Mockito.when(credentialRepository.findByPhone(anyString()))
                .thenAnswer(a -> {
                    if (a.getArgument(0).toString().equals(phone))
                        return Optional.of(credential);
                    else return Optional.empty();
                });

        Mockito.when(credentialRepository.findById(anyString()))
                .then(a -> {
                    if (a.getArgument(0).toString().equals(credentialId))
                        return Optional.of(credential);
                    return Optional.empty();
                });
    }

    private void initConfirmedCodeMocks() {
        Mockito.when(confirmationCodeRepository.existNotConfirmedCode(anyString()))
                .then(a -> false);

        Mockito.when(confirmationCodeRepository.findById(anyString()))
                .then(a -> {
                    if (a.getArgument(0).toString().equals(confirmationCodeId))
                        return Optional.of(confirmationCode);
                    else return Optional.empty();
                });

        Mockito.when(confirmationCodeRepository.findNotConfirmedByAddressAndSendingTypeAndCode(anyString(), any(ConfirmationCodeEntity.Recipient.SendingType.class), anyString()))
                .then(a -> {
                    if (a.getArgument(0).toString().equals(phone) || a.getArgument(0).toString().equals(email)
                            && a.getArgument(0).toString().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS.name())
                            && a.getArgument(0).toString().equals(code))
                        return Optional.of(confirmationCode);
                    else return Optional.empty();
                });
    }

    private void initAuthorizationMocks() {
        Mockito.when(authorizationRepository.findById(anyString()))
                .then(a -> {
                    if (a.getArgument(0).toString().equals(authorizationId))
                        return Optional.of(pendingAuthorization);
                    else return Optional.empty();
                });
    }
}

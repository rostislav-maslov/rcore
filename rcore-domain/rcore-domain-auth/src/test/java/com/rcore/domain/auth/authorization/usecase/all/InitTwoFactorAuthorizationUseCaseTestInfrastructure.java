package com.rcore.domain.auth.authorization.usecase.all;

import com.rcore.domain.auth.authorization.usecase.AuthorizationUseCaseTestInfrastructure;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;

class InitTwoFactorAuthorizationUseCaseTestInfrastructure extends AuthorizationUseCaseTestInfrastructure {

    protected static final String id = UUID.randomUUID().toString();
    protected static final String email = "mail@mail";
    protected static final String phone = "79998887766";

    protected final CredentialEntity credential;


    public InitTwoFactorAuthorizationUseCaseTestInfrastructure() {
        initCredentialMocks();
        initConfirmedCodeMocks();
        CredentialEntity defaultCredential = new CredentialEntity();
        defaultCredential.setId(id);
        defaultCredential.setEmail(email);
        defaultCredential.setPhone(phone);
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
    }

    private void initConfirmedCodeMocks() {
        Mockito.when(confirmationCodeRepository.existNotConfirmedCode(anyString()))
                .then(a -> false);
    }
}
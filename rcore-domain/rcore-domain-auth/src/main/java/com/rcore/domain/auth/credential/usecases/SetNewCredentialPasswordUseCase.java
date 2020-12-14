package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.passwordRecovery.entity.PasswordRecoveryEntity;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class SetNewCredentialPasswordUseCase extends UseCase<SetNewCredentialPasswordUseCase.InputValues, SingletonEntityOutputValues<CredentialEntity>> {

    private final CredentialRepository credentialRepository;
    private final PasswordCryptographer passwordCryptographer;

    @Override
    public SingletonEntityOutputValues<CredentialEntity> execute(InputValues inputValues) {
        CredentialEntity credentialEntity = credentialRepository.findById(inputValues.getId())
                .orElseThrow(() -> new CredentialNotFoundException(inputValues.getId()));

        credentialEntity.setPassword(passwordCryptographer.encrypt(inputValues.getNewPassword(), credentialEntity.getId()));
        return SingletonEntityOutputValues.of(credentialRepository.save(credentialEntity));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String id;
        private final String newPassword;
    }

}

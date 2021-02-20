package com.rcore.domain.auth.credential.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.credential.exceptions.CredentialPasswordIsRequiredException;
import com.rcore.domain.auth.credential.exceptions.InvalidOldCredentialPasswordException;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.*;

/**
 * Изменение пароля учетных данных владельцем
 */
@RequiredArgsConstructor
public class ChangeCredentialPasswordByOwnerUseCase extends UseCase<ChangeCredentialPasswordByOwnerUseCase.InputValues, SingletonEntityOutputValues<CredentialEntity>> {

    private final CredentialRepository credentialRepository;
    private final PasswordCryptographer passwordCryptographer;

    @Override
    public SingletonEntityOutputValues<CredentialEntity> execute(InputValues inputValues) {

        validate(inputValues);

        CredentialEntity credentialEntity = credentialRepository.findById(inputValues.getId())
                .orElseThrow(() -> new CredentialNotFoundException(inputValues.getId()));

        if (passwordCryptographer.validate(inputValues.getOldPassword(), credentialEntity.getPassword(), credentialEntity.getId()))
            throw new InvalidOldCredentialPasswordException();

        credentialEntity.setPassword(passwordCryptographer.encrypt(inputValues.getNewPassword(), credentialEntity.getId()));
        return SingletonEntityOutputValues.of(credentialRepository.save(credentialEntity ));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        private String id;
        private String oldPassword;
        private String newPassword;
    }

    private void validate(InputValues inputValues) {
        if (!StringUtils.hasText(inputValues.getNewPassword()))
            throw new CredentialPasswordIsRequiredException();
    }

}

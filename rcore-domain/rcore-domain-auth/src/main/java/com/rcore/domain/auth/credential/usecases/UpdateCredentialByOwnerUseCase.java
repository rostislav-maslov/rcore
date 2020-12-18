package com.rcore.domain.auth.credential.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.credential.exceptions.CredentialWithEmailAlreadyExistException;
import com.rcore.domain.auth.credential.exceptions.CredentialWithPhoneAlreadyExistException;
import com.rcore.domain.auth.credential.exceptions.CredentialWithUsernameAlreadyExistException;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.*;

import java.util.Optional;

/**
 * Изменение СВОИХ учетных данных
 */
@RequiredArgsConstructor
public class UpdateCredentialByOwnerUseCase extends UseCase<UpdateCredentialByOwnerUseCase.InputValues, SingletonEntityOutputValues<CredentialEntity>> {

    private final CredentialRepository credentialRepository;
    private final FindCredentialByPhoneUseCase findCredentialByPhoneUseCase;
    private final FindCredentialByEmailUseCase findCredentialByEmailUseCase;
    private final FindCredentialByUsernameUseCase findCredentialByUsernameUseCase;

    @Override
    public SingletonEntityOutputValues<CredentialEntity> execute(InputValues inputValues) {
        CredentialEntity credentialEntity = credentialRepository.findById(inputValues.getId())
                .orElseThrow(() -> new CredentialNotFoundException(inputValues.getId()));

        validate(credentialEntity, inputValues);

        credentialEntity.setPhone(inputValues.getPhone());
        credentialEntity.setUsername(inputValues.getUsername());
        credentialEntity.setEmail(inputValues.getEmail());

        return SingletonEntityOutputValues.of(credentialRepository.save(credentialEntity));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        private String id;
        private String phone;
        private String username;
        private String email;
    }

    private void validate(CredentialEntity credentialEntity, InputValues inputValues) {
        if (StringUtils.hasText(inputValues.getPhone())) {
            Optional<CredentialEntity> credentialWithPhone = findCredentialByPhoneUseCase.execute(FindCredentialByPhoneUseCase.InputValues.of(inputValues.getPhone()))
                    .getCredentialEntity();
            if (credentialWithPhone.isPresent() && !credentialWithPhone.get().getId().equals(credentialEntity.getId()))
                throw new CredentialWithPhoneAlreadyExistException(inputValues.getPhone());
        }

        if (StringUtils.hasText(inputValues.getEmail())) {
            Optional<CredentialEntity> credentialWithEmail = findCredentialByEmailUseCase.execute(FindCredentialByEmailUseCase.InputValues.of(inputValues.getEmail()))
                    .getEntity();
            if (credentialWithEmail.isPresent() && !credentialWithEmail.get().getId().equals(credentialEntity.getId()))
                throw new CredentialWithEmailAlreadyExistException(inputValues.getEmail());
        }

        if (StringUtils.hasText(inputValues.getUsername())) {
            Optional<CredentialEntity> credentialWithEmail = findCredentialByUsernameUseCase.execute(FindCredentialByUsernameUseCase.InputValues.of(inputValues.getUsername()))
                    .getEntity();
            if (credentialWithEmail.isPresent() && !credentialWithEmail.get().getId().equals(credentialEntity.getId()))
                throw new CredentialWithUsernameAlreadyExistException(inputValues.getUsername());
        }
    }
}

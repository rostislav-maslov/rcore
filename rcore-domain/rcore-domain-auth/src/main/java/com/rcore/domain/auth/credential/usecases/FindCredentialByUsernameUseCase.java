package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.commons.usecase.model.SingletonOptionalEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class FindCredentialByUsernameUseCase extends UseCase<FindCredentialByUsernameUseCase.InputValues, SingletonOptionalEntityOutputValues<CredentialEntity>> {

    private final CredentialRepository credentialRepository;

    @Override
    public SingletonOptionalEntityOutputValues<CredentialEntity> execute(InputValues inputValues) {
        return SingletonOptionalEntityOutputValues.of(credentialRepository.findByUsername(inputValues.getUsername()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String username;
    }

}

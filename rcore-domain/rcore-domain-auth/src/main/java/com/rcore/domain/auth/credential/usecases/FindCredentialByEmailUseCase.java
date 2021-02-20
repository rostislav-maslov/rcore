package com.rcore.domain.auth.credential.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.commons.usecase.model.SingletonOptionalEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Optional;

@RequiredArgsConstructor
public class FindCredentialByEmailUseCase extends UseCase<FindCredentialByEmailUseCase.InputValues, SingletonOptionalEntityOutputValues<CredentialEntity>> {

    private final CredentialRepository credentialRepository;

    @Override
    public SingletonOptionalEntityOutputValues<CredentialEntity> execute(InputValues inputValues) {
        if (!StringUtils.hasText(inputValues.getEmail()))
            return SingletonOptionalEntityOutputValues.empty();

        return SingletonOptionalEntityOutputValues.of(credentialRepository.findByEmail(inputValues.getEmail()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String email;
    }

}

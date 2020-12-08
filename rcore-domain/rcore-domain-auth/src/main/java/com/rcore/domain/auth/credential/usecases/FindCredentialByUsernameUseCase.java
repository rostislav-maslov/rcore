package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class FindCredentialByUsernameUseCase extends UseCase<FindCredentialByUsernameUseCase.InputValues, AbstractFindByIdUseCase.OutputValues<CredentialEntity>> {

    private final CredentialRepository credentialRepository;

    @Override
    public AbstractFindByIdUseCase.OutputValues<CredentialEntity> execute(InputValues inputValues) {
        return AbstractFindByIdUseCase.OutputValues.of(credentialRepository.findByUsername(inputValues.getUsername()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String username;
    }

}

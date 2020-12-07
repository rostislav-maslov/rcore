package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.usecases.GetAuthorizationByIdUseCase;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Optional;

@RequiredArgsConstructor
public class GetCredentialByIdUseCase extends UseCase<GetCredentialByIdUseCase.InputValues, GetCredentialByIdUseCase.OutputValues> {

    private final CredentialRepository credentialRepository;

    @Override
    public GetCredentialByIdUseCase.OutputValues execute(GetCredentialByIdUseCase.InputValues inputValues) {
        if (inputValues.getId() == null)
            return GetCredentialByIdUseCase.OutputValues.empty();

        return GetCredentialByIdUseCase.OutputValues.of(credentialRepository.findById(inputValues.getId()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String id;
    }

    @Value(staticConstructor = "of")
    public static class OutputValues implements UseCase.OutputValues {
        private final Optional<CredentialEntity> credentialEntity;

        public static GetCredentialByIdUseCase.OutputValues empty() {
            return new GetCredentialByIdUseCase.OutputValues(Optional.empty());
        }

    }

}

package com.rcore.domain.auth.credential.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Optional;

@RequiredArgsConstructor
public class FindCredentialByEmailUseCase extends UseCase<FindCredentialByEmailUseCase.InputValues, FindCredentialByEmailUseCase.OutputValues> {

    private final CredentialRepository credentialRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {
        if (!StringUtils.hasText(inputValues.getEmail()))
            return OutputValues.empty();

        return new OutputValues(credentialRepository.findByEmail(inputValues.getEmail()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String email;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {

        private final Optional<CredentialEntity> credentialEntity;

        public static OutputValues empty() {
            return new OutputValues(Optional.empty());
        }

    }

}

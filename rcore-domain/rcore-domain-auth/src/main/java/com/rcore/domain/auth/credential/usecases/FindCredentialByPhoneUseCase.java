package com.rcore.domain.auth.credential.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Optional;

@RequiredArgsConstructor
public class FindCredentialByPhoneUseCase extends UseCase<FindCredentialByPhoneUseCase.InputValues, FindCredentialByPhoneUseCase.OutputValues> {

    private final CredentialRepository credentialRepository;

    @Override
    public FindCredentialByPhoneUseCase.OutputValues execute(FindCredentialByPhoneUseCase.InputValues inputValues) {
        if (!StringUtils.hasText(inputValues.getPhone()))
            return FindCredentialByPhoneUseCase.OutputValues.empty();

        return new FindCredentialByPhoneUseCase.OutputValues(credentialRepository.findByPhone(inputValues.getPhone()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String phone;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {

        private final Optional<CredentialEntity> credentialEntity;

        public static FindCredentialByPhoneUseCase.OutputValues empty() {
            return new FindCredentialByPhoneUseCase.OutputValues(Optional.empty());
        }

    }

}

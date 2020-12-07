package com.rcore.domain.auth.credential.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Optional;

@RequiredArgsConstructor
public class GetCredentialByPhoneUseCase extends UseCase<GetCredentialByPhoneUseCase.InputValues, GetCredentialByPhoneUseCase.OutputValues> {

    private final CredentialRepository credentialRepository;

    @Override
    public GetCredentialByPhoneUseCase.OutputValues execute(GetCredentialByPhoneUseCase.InputValues inputValues) {
        if (!StringUtils.hasText(inputValues.getPhone()))
            return GetCredentialByPhoneUseCase.OutputValues.empty();

        return new GetCredentialByPhoneUseCase.OutputValues(credentialRepository.findByPhone(inputValues.getPhone()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String phone;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {

        private final Optional<CredentialEntity> credentialEntity;

        public static GetCredentialByPhoneUseCase.OutputValues empty() {
            return new GetCredentialByPhoneUseCase.OutputValues(Optional.empty());
        }

    }

}

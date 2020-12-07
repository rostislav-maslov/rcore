package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Optional;

@RequiredArgsConstructor
public class GetAuthorizationByIdUseCase extends UseCase<GetAuthorizationByIdUseCase.InputValues, GetAuthorizationByIdUseCase.OutputValues>{

    private final AuthorizationRepository authorizationRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {
        if (inputValues.getId() == null)
            return OutputValues.empty();

        return OutputValues.of(authorizationRepository.findById(inputValues.getId()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String id;
    }

    @Value(staticConstructor = "of")
    public static class OutputValues implements UseCase.OutputValues {
        private final Optional<AuthorizationEntity> authorizationEntity;

        public static OutputValues empty() {
            return new OutputValues(Optional.empty());
        }

    }
}

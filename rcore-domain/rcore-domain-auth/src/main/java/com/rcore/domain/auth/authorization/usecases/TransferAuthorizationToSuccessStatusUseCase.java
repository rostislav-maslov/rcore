package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.exceptions.AuthorizationNotFoundException;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class TransferAuthorizationToSuccessStatusUseCase extends UseCase<TransferAuthorizationToSuccessStatusUseCase.InputValues, TransferAuthorizationToSuccessStatusUseCase.OutputValues> {

    private final AuthorizationRepository authorizationRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {
        AuthorizationEntity authorizationEntity = authorizationRepository.findById(inputValues.getAuthorizationId())
                .orElseThrow(() -> new AuthorizationNotFoundException(inputValues.getAuthorizationId()));

        authorizationEntity.setSuccess(inputValues.getAccessTokeId(), inputValues.getRefreshTokeId());
        return new OutputValues(authorizationRepository.save(authorizationEntity));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String accessTokeId;
        private final String refreshTokeId;
        private final String authorizationId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final AuthorizationEntity authorizationEntity;
    }

}

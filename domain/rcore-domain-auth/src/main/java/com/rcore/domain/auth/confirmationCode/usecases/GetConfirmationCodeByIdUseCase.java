package com.rcore.domain.auth.confirmationCode.usecases;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Optional;

@RequiredArgsConstructor
public class GetConfirmationCodeByIdUseCase extends UseCase<GetConfirmationCodeByIdUseCase.InputValues, GetConfirmationCodeByIdUseCase.OutputValues> {

    private final ConfirmationCodeRepository confirmationCodeRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {
        if (inputValues.getId() == null)
            return OutputValues.empty();

        return new OutputValues(confirmationCodeRepository.findById(inputValues.getId()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final Optional<ConfirmationCodeEntity> confirmationCodeEntity;

        public static OutputValues empty() {
            return new OutputValues(Optional.empty());
        }
    }

}

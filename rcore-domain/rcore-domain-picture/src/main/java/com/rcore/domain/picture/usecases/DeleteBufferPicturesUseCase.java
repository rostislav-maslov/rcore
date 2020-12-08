package com.rcore.domain.picture.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

public class DeleteBufferPicturesUseCase extends UseCase<DeleteBufferPicturesUseCase.InputValues, DeleteBufferPicturesUseCase.OutputValues> {

    @Override
    public OutputValues execute(InputValues inputValues) {
        return null;
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String id;
    }

    public static class OutputValues implements UseCase.OutputValues {

    }

}

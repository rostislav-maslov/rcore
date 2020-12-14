package com.rcore.domain.picture.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonOptionalEntityOutputValues;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class FindPictureByPathUseCase extends UseCase<FindPictureByPathUseCase.InputValues, SingletonOptionalEntityOutputValues<PictureEntity>> {

    private final PictureRepository repository;

    @Override
    public SingletonOptionalEntityOutputValues<PictureEntity> execute(InputValues inputValues) {
        return SingletonOptionalEntityOutputValues.of(repository.findByPath(inputValues.getPath()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String path;
    }

}

package com.rcore.domain.file.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonOptionalEntityOutputValues;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class FindFileByPathUseCase extends UseCase<FindFileByPathUseCase.InputValues, SingletonOptionalEntityOutputValues<FileEntity>> {

    private final FileRepository repository;

    @Override
    public SingletonOptionalEntityOutputValues<FileEntity> execute(InputValues inputValues) {
        return SingletonOptionalEntityOutputValues.of(repository.findByPath(inputValues.getPath()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String path;
    }

}

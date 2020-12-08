package com.rcore.domain.file.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class FindFileByPathUseCase extends UseCase<FindFileByPathUseCase.InputValues, AbstractFindByIdUseCase.OutputValues<FileEntity>> {

    private final FileRepository repository;

    @Override
    public AbstractFindByIdUseCase.OutputValues<FileEntity> execute(InputValues inputValues) {
        return AbstractFindByIdUseCase.OutputValues.of(repository.findByPath(inputValues.getPath()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String path;
    }

}

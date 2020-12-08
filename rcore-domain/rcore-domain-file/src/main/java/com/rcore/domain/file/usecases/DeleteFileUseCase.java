package com.rcore.domain.file.usecases;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exceptions.FileNotFoundException;
import com.rcore.domain.file.port.FileRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteFileUseCase extends UseCase<AbstractDeleteUseCase.InputValues<String>, AbstractDeleteUseCase.OutputValues> {

    private final FileRepository repository;
    private final FileStorage fileStorage;

    @Override
    public AbstractDeleteUseCase.OutputValues execute(AbstractDeleteUseCase.InputValues<String> stringInputValues) {

        FileEntity fileEntity = repository.findById(stringInputValues.getId())
                .orElseThrow(() -> new FileNotFoundException(stringInputValues.getId()));

        fileStorage.remove(fileEntity.getFilePath());

        return AbstractDeleteUseCase.OutputValues.of(repository.delete(fileEntity.getId()));
    }
}

package com.rcore.domain.file.usecases;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exceptions.FileNotFoundException;
import com.rcore.domain.file.port.FileRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteFileUseCase extends UseCase<IdInputValues<String>, AbstractDeleteUseCase.OutputValues> {

    private final FileRepository repository;
    private final FileStorage fileStorage;

    @Override
    public AbstractDeleteUseCase.OutputValues execute(IdInputValues<String> stringIdInputValues) {
        FileEntity fileEntity = repository.findById(stringIdInputValues.getId())
                .orElseThrow(() -> new FileNotFoundException(stringIdInputValues.getId()));

        fileStorage.remove(fileEntity.getFilePath());

        return AbstractDeleteUseCase.OutputValues.of(repository.delete(fileEntity.getId()));
    }
}

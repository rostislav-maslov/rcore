package com.rcore.domain.file.usecases;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exceptions.InvalidFileDataException;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import lombok.Value;

import java.io.InputStream;

public class CreateFileUseCase extends AbstractCreateUseCase<FileEntity, FileIdGenerator, FileRepository, CreateFileUseCase.InputValues> {

    private final FileStorage fileStorage;

    public CreateFileUseCase(FileRepository repository, FileIdGenerator idGenerator, FileStorage fileStorage) {
        super(repository, idGenerator);
        this.fileStorage = fileStorage;
    }

    @Override
    public SingletonEntityOutputValues<FileEntity> execute(InputValues inputValues) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(idGenerator.generate());
        fileEntity.setIsPrivate(inputValues.getIsPrivate());
        fileEntity.setFileName(inputValues.getFileName());
        fileEntity.setDataFormat(inputValues.getDataFormat());
        fileEntity.setFilePath(fileStorage.store(inputValues.getInputStream(), inputValues.getFileName(), inputValues.getDataFormat()));
        return SingletonEntityOutputValues.of(repository.save(fileEntity));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final InputStream inputStream;
        private final String fileName;
        private final String dataFormat;
        private final Boolean isPrivate;
    }

    private void validate(InputValues inputValues) {
        if (inputValues.getInputStream() == null || inputValues.getFileName() == null || inputValues.getDataFormat() == null)
            throw new InvalidFileDataException();

    }

}

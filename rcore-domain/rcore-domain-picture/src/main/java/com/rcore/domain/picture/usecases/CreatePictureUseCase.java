package com.rcore.domain.picture.usecases;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exceptions.InvalidPictureDataException;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import lombok.Value;

import java.io.InputStream;

public class CreatePictureUseCase extends AbstractCreateUseCase<PictureEntity, PictureIdGenerator, PictureRepository, CreatePictureUseCase.InputValues> {

    private final FileStorage fileStorage;

    public CreatePictureUseCase(PictureRepository repository, PictureIdGenerator idGenerator, FileStorage fileStorage) {
        super(repository, idGenerator);
        this.fileStorage = fileStorage;
    }

    @Override
    public OutputValues<PictureEntity> execute(InputValues inputValues) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setId(idGenerator.generate());
        pictureEntity.setIsPrivate(inputValues.getIsPrivate());
        pictureEntity.setIsBuffer(inputValues.getIsBuffer());
        pictureEntity.setFileName(inputValues.getFileName());
        pictureEntity.setDataFormat(inputValues.getDataFormat());
        pictureEntity.setFilePath(fileStorage.store(inputValues.getInputStream(), inputValues.getFileName(), inputValues.getDataFormat()));
        return OutputValues.of(repository.save(pictureEntity));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final InputStream inputStream;
        private final String fileName;
        private final String dataFormat;
        private final Boolean isPrivate;
        private final Boolean isBuffer;
    }

    private void validate(InputValues inputValues) {
        if (inputValues.getInputStream() == null || inputValues.getFileName() == null || inputValues.getDataFormat() == null)
            throw new InvalidPictureDataException();

    }

}

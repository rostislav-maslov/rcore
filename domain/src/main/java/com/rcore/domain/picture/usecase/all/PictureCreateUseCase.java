package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;

import java.io.File;
import java.io.InputStream;

public class PictureCreateUseCase extends PictureBaseUseCase {

    private final PictureIdGenerator pictureIdGenerator;
    private final PictureStorage pictureStorage;

    public PictureCreateUseCase(PictureRepository pictureRepository, PictureIdGenerator pictureIdGenerator, PictureStorage pictureStorage) {
        super(pictureRepository);
        this.pictureIdGenerator = pictureIdGenerator;
        this.pictureStorage = pictureStorage;
    }

    public PictureEntity create(InputStream content, String fileName, String contentType, Boolean isUsed) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setId(pictureIdGenerator.generate());
        pictureEntity.setIsPrivate(false);
        pictureEntity.setFileName(fileName);
        pictureEntity.setIsUsed(isUsed);
        pictureEntity.setFilePath(pictureStorage.store(content, fileName, contentType));

        return pictureRepository.save(pictureEntity);
    }
}

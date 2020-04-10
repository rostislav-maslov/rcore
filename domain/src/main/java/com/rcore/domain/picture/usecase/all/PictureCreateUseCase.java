package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;

import java.io.File;

public class PictureCreateUseCase extends PictureBaseUseCase {

    private final PictureIdGenerator pictureIdGenerator;
    private final PictureStorage pictureStorage;

    public PictureCreateUseCase(PictureRepository pictureRepository, PictureIdGenerator pictureIdGenerator, PictureStorage pictureStorage) {
        super(pictureRepository);
        this.pictureIdGenerator = pictureIdGenerator;
        this.pictureStorage = pictureStorage;
    }

    public PictureEntity create(File file) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setId(pictureIdGenerator.generate());
        pictureEntity.setIsPrivate(false);
        pictureEntity.setFileName(file.getName());
        pictureEntity.setFilePath(pictureStorage.store(file));

        return pictureRepository.save(pictureEntity);
    }
}

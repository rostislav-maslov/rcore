package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exception.PictureAccessException;
import com.rcore.domain.picture.exception.PictureNotFoundException;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;

import java.io.InputStream;
import java.util.Optional;

public class PictureViewUseCase extends PictureBaseUseCase {

    private final PictureStorage pictureStorage;

    public PictureViewUseCase(PictureRepository pictureRepository, PictureStorage pictureStorage) {
        super(pictureRepository);
        this.pictureStorage = pictureStorage;
    }

    public Optional<PictureEntity> findById(String id) throws PictureAccessException {
        Optional<PictureEntity> pictureEntity = pictureRepository.findById(id);

        if (pictureEntity.isPresent() && pictureEntity.get().getIsPrivate())
            throw new PictureAccessException();

        return pictureEntity;
    }

    public Optional<InputStream> getInputStream(String id) throws PictureNotFoundException, PictureAccessException {
        PictureEntity pictureEntity = findById(id)
                .orElseThrow(() -> new PictureNotFoundException());

        return pictureStorage.getInputStream(pictureEntity.getFilePath());
    }

    public Optional<InputStream> getInputStreamByWidth(String id, Integer width) throws PictureAccessException {

        Optional<PictureEntity> pictureEntity = findById(id);

        Optional<String> filePath = pictureEntity.get().getSizes()
                .values()
                .stream()
                .filter(size -> size.getWidth().equals(width))
                .findFirst()
                .map(PictureEntity.Size::getFilePath);

        if (!filePath.isPresent())
            return Optional.empty();

        return pictureStorage.getInputStream(pictureEntity.get().getFilePath());
    }
}

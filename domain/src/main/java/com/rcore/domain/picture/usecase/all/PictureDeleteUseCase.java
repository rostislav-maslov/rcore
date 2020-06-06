package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exception.PictureNotFoundException;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;

public class PictureDeleteUseCase extends PictureBaseUseCase {

    private final PictureStorage pictureStorage;

    public PictureDeleteUseCase(PictureRepository pictureRepository, PictureStorage pictureStorage) {
        super(pictureRepository);
        this.pictureStorage = pictureStorage;
    }

    public Boolean delete(PictureEntity pictureEntity) {
        if (pictureEntity.getFilePath() != null && pictureEntity.getFilePath().length() > 0)
            pictureStorage.remove(pictureEntity.getFilePath());

        //Удаление всех размеров изображения
        if (pictureEntity.getSizes() != null && pictureEntity.getSizes().size() > 0)
            pictureEntity.getSizes().values()
                    .stream()
                    .forEach(size -> pictureStorage.remove(size.getFilePath()));

        pictureRepository.delete(pictureEntity);

        return true;
    }

    public Boolean deleteById(String id) throws PictureNotFoundException {
        return delete(pictureRepository.findById(id)
                .orElseThrow(PictureNotFoundException::new));
    }
}

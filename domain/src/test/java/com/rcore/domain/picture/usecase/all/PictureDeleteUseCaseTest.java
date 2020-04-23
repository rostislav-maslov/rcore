package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.picture.PictureAppConfig;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exception.PictureAccessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

public class PictureDeleteUseCaseTest {

    private final PictureAppConfig pictureAppConfig = new PictureAppConfig();
    private PictureEntity pictureEntity;

    @Before
    public void setUp() throws FileNotFoundException {
        File file = new File(pictureAppConfig.getFILE_PATH());
        pictureEntity = pictureAppConfig.getPictureConfig().all.createUseCase().create(new FileInputStream(file), file.getName(), "jpg");
    }

    @Test
    public void delete() throws PictureAccessException {
        Optional<PictureEntity> picture = pictureAppConfig.getPictureConfig().all.viewUseCase().findById(pictureEntity.getId());
        Assert.assertTrue(picture.isPresent());

        pictureAppConfig.getPictureConfig().all.deleteUseCase().delete(pictureEntity);

        picture = pictureAppConfig.getPictureConfig().all.viewUseCase().findById(pictureEntity.getId());
        Assert.assertFalse("Изображение не удалено", picture.isPresent());
        return;
    }
}
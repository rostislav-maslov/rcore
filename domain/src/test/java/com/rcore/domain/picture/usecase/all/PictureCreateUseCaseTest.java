package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.picture.PictureAppConfig;
import com.rcore.domain.picture.entity.PictureEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PictureCreateUseCaseTest {

    private final PictureAppConfig pictureAppConfig = new PictureAppConfig();

    @Test
    public void create() throws FileNotFoundException {
        File file = new File(pictureAppConfig.getFILE_PATH());
        PictureEntity pictureEntity = pictureAppConfig.getPictureConfig().all.createUseCase().create(new FileInputStream(file), file.getName(), "jpg", false);
        Assert.assertNotNull("Изображение не создано", pictureEntity);
    }
}
package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.picture.PictureAppConfig;
import com.rcore.domain.picture.entity.PictureEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PictureCreateUseCaseTest {

    private final PictureAppConfig pictureAppConfig = new PictureAppConfig();

    @Test
    public void create() {
        PictureEntity pictureEntity = pictureAppConfig.getPictureConfig().all.createUseCase().create(new File(pictureAppConfig.getFILE_PATH()));
        Assert.assertNotNull("Изображение не создано", pictureEntity);
    }
}
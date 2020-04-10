package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.picture.PictureAppConfig;
import com.rcore.domain.picture.entity.PictureEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class PictureCreateUseCaseTest {

    private final PictureAppConfig pictureAppConfig = new PictureAppConfig();

    @Test
    public void create() throws FileNotFoundException {
        File file = new File(pictureAppConfig.getFILE_PATH());
        PictureEntity pictureEntity = pictureAppConfig.getPictureConfig().all.createUseCase().create(new FileInputStream(file), file.getName(), "jpg");
        Assert.assertNotNull("Изображение не создано", pictureEntity);
    }
}
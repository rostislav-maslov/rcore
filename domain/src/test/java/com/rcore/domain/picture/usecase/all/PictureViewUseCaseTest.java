package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.picture.PictureAppConfig;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exception.PictureAccessException;
import com.rcore.domain.picture.exception.PictureNotFoundException;
import com.rcore.domain.picture.testdata.PictureViewTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

public class PictureViewUseCaseTest {

    private final PictureAppConfig pictureAppConfig = new PictureAppConfig();
    private PictureEntity pictureEntity;

    @Before
    public void setUp() throws FileNotFoundException {
        File file = new File(pictureAppConfig.getFILE_PATH());
        pictureEntity = pictureAppConfig.getPictureRepository().save(PictureViewTestData.create(pictureAppConfig.getFILE_PATH(), pictureAppConfig.getPictureIdGenerator()));
        pictureAppConfig.getPictureStorage().store(new FileInputStream(file), file.getName(), "jpg");
    }

    @Test
    public void findById() throws PictureAccessException {
        Optional<PictureEntity> picture = pictureAppConfig.getPictureConfig().all.viewUseCase().findById(pictureEntity.getId());
        Assert.assertTrue(picture.isPresent());
        Assert.assertEquals(pictureEntity.getId(), picture.get().getId());
    }

    @Test
    public void getInputStream() throws PictureAccessException, PictureNotFoundException {
        Optional<InputStream> inputStream = pictureAppConfig.getPictureConfig().all.viewUseCase().getInputStream(pictureEntity.getId());
        Assert.assertTrue(inputStream.isPresent());
    }

    @Test
    public void getInputStreamByWidth() throws PictureAccessException, PictureNotFoundException {
        Optional<InputStream> inputStream = pictureAppConfig.getPictureConfig().all.viewUseCase().getInputStreamByWidth(pictureEntity.getId(), 100);
        Assert.assertTrue(inputStream.isPresent());
    }
}
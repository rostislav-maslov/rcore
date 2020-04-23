package com.rcore.domain.file.usecase.all;

import com.rcore.domain.file.FileAppConfig;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileAccessException;
import com.rcore.domain.file.exception.FileNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

public class FileViewUseCaseTest {

    private final FileAppConfig fileAppConfig = new FileAppConfig();
    private FileEntity fileEntity;

    @Before
    public void setUp() throws Exception {
        File file = new File(fileAppConfig.getFILE_PATH());
        fileEntity = fileAppConfig.getFileConfig().all.createUseCase().create(new FileInputStream(file), file.getName(), "jpg");
    }

    @Test
    public void findById() throws FileAccessException {
        Optional<FileEntity> file = fileAppConfig.getFileConfig().all.viewUseCase().findById(fileEntity.getId());
        Assert.assertTrue(file.isPresent());
        Assert.assertEquals(fileEntity.getId(), file.get().getId());
    }

    @Test
    public void getInputStream() throws FileNotFoundException, FileAccessException {
        Optional<InputStream> inputStream = fileAppConfig.getFileConfig().all.viewUseCase().getInputStream(fileEntity.getId());
        Assert.assertTrue(inputStream.isPresent());
    }
}
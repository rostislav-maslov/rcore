package com.rcore.domain.file.usecase.all;

import com.rcore.domain.file.FileAppConfig;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileAccessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Optional;

import static org.junit.Assert.*;

public class FileDeleteUseCaseTest {

    private final FileAppConfig fileAppConfig = new FileAppConfig();

    private FileEntity fileEntity;

    @Before
    public void before() {
       fileEntity = fileAppConfig.getFileConfig().all.createUseCase().create(new File(fileAppConfig.getFILE_PATH()));
    }

    @Test
    public void delete() throws FileAccessException {
        Optional<FileEntity> file = fileAppConfig.getFileConfig().all.viewUseCase().findById(fileEntity.getId());
        Assert.assertTrue(file.isPresent());

        fileAppConfig.getFileConfig().all.deleteUseCase().delete(fileEntity);

        file = fileAppConfig.getFileConfig().all.viewUseCase().findById(fileEntity.getId());
        Assert.assertFalse("Файл не удален", file.isPresent());
        return;
    }
}
package com.rcore.domain.file.usecase.all;

import com.rcore.domain.file.FileAppConfig;
import com.rcore.domain.file.entity.FileEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class FileCreateUseCaseTest {

    private final FileAppConfig fileAppConfig = new FileAppConfig();

    @Test
    public void create() throws FileNotFoundException {
        File file = new File(fileAppConfig.getFILE_PATH());
        FileEntity fileEntity = fileAppConfig.getFileConfig().all.createUseCase().create(new FileInputStream(file), file.getName(), "jpg");
        Assert.assertNotNull("Файл не создан", fileEntity);
    }
}
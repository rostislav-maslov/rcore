package com.rcore.domain.picture;

import com.rcore.domain.database.memory.picture.port.PictureCompressorImpl;
import com.rcore.domain.database.memory.picture.port.PictureUserIdGeneratorImpl;
import com.rcore.domain.database.memory.picture.port.PictureRepositoryImpl;
import com.rcore.domain.database.memory.picture.port.PictureStorageImpl;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import lombok.Getter;

@Getter
public class PictureAppConfig {

    private final String FILE_PATH = "src/test/java/com/rcore/resources/file.jpg";

    private final PictureConfig pictureConfig;
    private final PictureRepository pictureRepository;
    private final PictureIdGenerator pictureIdGenerator;
    private final PictureStorage pictureStorage;
    private final PictureCompressor pictureCompressor;

    public PictureAppConfig() {
        this.pictureRepository = new PictureRepositoryImpl();
        this.pictureIdGenerator = new PictureUserIdGeneratorImpl();
        this.pictureStorage = new PictureStorageImpl();
        this.pictureCompressor = new PictureCompressorImpl(pictureStorage);

        this.pictureConfig = new PictureConfig(this.pictureRepository, this.pictureIdGenerator, this.pictureStorage, this.pictureCompressor);
    }
}

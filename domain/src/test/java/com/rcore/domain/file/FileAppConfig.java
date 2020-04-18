package com.rcore.domain.file;

import com.rcore.domain.database.memory.file.port.FileUserIdGeneratorImpl;
import com.rcore.domain.database.memory.file.port.FileRepositoryImpl;
import com.rcore.domain.database.memory.file.port.FileStorageImpl;
import com.rcore.domain.file.config.FileConfig;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import lombok.Getter;

@Getter
public class FileAppConfig {

    private final String FILE_PATH = "src/test/java/com/rcore/resources/file.jpg";

    private final FileConfig fileConfig;
    private final FileIdGenerator idGenerator;
    private final FileRepository fileRepository;
    private final FileStorage fileStorage;

    public FileAppConfig() {
        this.idGenerator = new FileUserIdGeneratorImpl();
        this.fileRepository = new FileRepositoryImpl();
        this.fileStorage = new FileStorageImpl();

        this.fileConfig = new FileConfig(fileRepository, idGenerator, fileStorage);
    }
}

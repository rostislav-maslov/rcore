package com.rcore.domain.file.usecase.all;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileStoreException;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;

import java.io.File;
import java.io.InputStream;

public class FileCreateUseCase extends FileBaseUseCase {
    private final FileIdGenerator fileIdGenerator;
    private final FileStorage fileStorage;

    public FileCreateUseCase(FileRepository fileRepository, FileIdGenerator fileIdGenerator, FileStorage fileStorage) {
        super(fileRepository);
        this.fileIdGenerator = fileIdGenerator;
        this.fileStorage = fileStorage;
    }

    public FileEntity create(InputStream content, String fileName, String contentType) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(fileIdGenerator.generate());
        fileEntity.setFileName(fileName);
        fileEntity.setIsPrivate(false);

        fileEntity.setFilePath(fileStorage.store(content, fileName, contentType));

        fileEntity = fileRepository.save(fileEntity);
        return fileEntity;
    }
}

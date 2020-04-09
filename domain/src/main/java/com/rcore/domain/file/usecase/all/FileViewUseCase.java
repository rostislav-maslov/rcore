package com.rcore.domain.file.usecase.all;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileAccessException;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;

import java.io.InputStream;
import java.util.Optional;

public class FileViewUseCase extends FileBaseUseCase {

    private final FileStorage fileStorage;

    public FileViewUseCase(FileRepository fileRepository, FileStorage fileStorage) {
        super(fileRepository);
        this.fileStorage = fileStorage;
    }

    public Optional<FileEntity> findById(String id) throws FileAccessException {
        Optional<FileEntity> fileEntity = fileRepository.findById(id);

        if (fileEntity.isPresent() && fileEntity.get().getIsPrivate())
            throw new FileAccessException();

        return fileEntity;
    }

    public Optional<InputStream> getInputStream(String id) throws FileNotFoundException, FileAccessException {
        FileEntity fileEntity = findById(id)
                .orElseThrow(() -> new FileNotFoundException());

        return fileStorage.getInputStream(fileEntity.getFilePath());
    }
}

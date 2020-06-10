package com.rcore.domain.file.usecase.all;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileAccessException;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;

public class FileDeleteUseCase extends FileBaseUseCase {

    private final FileStorage fileStorage;

    public FileDeleteUseCase(FileRepository fileRepository, FileStorage fileStorage) {
        super(fileRepository);
        this.fileStorage = fileStorage;
    }

    public Boolean deleteById(String id) throws FileNotFoundException, FileAccessException {
        return delete(fileRepository.findById(id)
                .orElseThrow(FileNotFoundException::new));
    }

    public Boolean delete(FileEntity fileEntity) throws FileAccessException {
        if (fileEntity.getIsPrivate())
            throw new FileAccessException();

        if (fileEntity.getFilePath() != null && fileEntity.getFilePath().length() > 0)
            fileStorage.remove(fileEntity.getFilePath());

        fileRepository.delete(fileEntity);

        return true;
    }
}

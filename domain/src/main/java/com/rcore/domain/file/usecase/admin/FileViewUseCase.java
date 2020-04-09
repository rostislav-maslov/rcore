package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.role.AdminFileViewRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;

import java.io.InputStream;
import java.util.Optional;

public class FileViewUseCase extends FileAdminBaseUseCase {

    private final FileStorage fileStorage;

    public FileViewUseCase(UserEntity actor, FileRepository fileRepository, FileStorage fileStorage) throws AuthorizationException {
        super(actor, fileRepository, new AdminFileViewRole());
        this.fileStorage = fileStorage;
    }

    public Optional<FileEntity> findById(String id) {
        return fileRepository.findById(id);
    }

    public Optional<FileEntity> search(String id) {
        return fileRepository.findById(id);
    }

    public SearchResult<FileEntity> find(Long size, Long skip) {
        return fileRepository.find(size, skip);
    }

    public Optional<InputStream> getInputStream(String id) throws FileNotFoundException {
        FileEntity fileEntity = findById(id)
                .orElseThrow(() -> new FileNotFoundException());

        return fileStorage.getInputStream(fileEntity.getFilePath());
    }

}
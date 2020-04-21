package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.access.AdminFileDeleteAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class FileDeleteUseCase extends FileAdminBaseUseCase {

    private final FileStorage fileStorage;

    public FileDeleteUseCase(UserEntity actor, FileRepository fileRepository, FileStorage fileStorage) throws AuthorizationException {
        super(actor, fileRepository, new AdminFileDeleteAccess());
        this.fileStorage = fileStorage;
    }

    public Boolean delete(FileEntity fileEntity) {
        if (fileEntity.getFilePath() != null && fileEntity.getFilePath().length() > 0)
            fileStorage.remove(fileEntity.getFilePath());

        fileRepository.delete(fileEntity);

        return true;
    }


}
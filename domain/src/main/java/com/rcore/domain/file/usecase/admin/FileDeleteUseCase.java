package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.access.AdminFileDeleteAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.TokenExpiredException;

public class FileDeleteUseCase extends FileAdminBaseUseCase {

    private final FileStorage fileStorage;

    public FileDeleteUseCase(FileRepository fileRepository, FileStorage fileStorage, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(fileRepository, new AdminFileDeleteAccess(), authorizationByTokenUseCase);
        this.fileStorage = fileStorage;
    }

    public Boolean delete(FileEntity fileEntity) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        if (fileEntity.getFilePath() != null && fileEntity.getFilePath().length() > 0)
            fileStorage.remove(fileEntity.getFilePath());

        fileRepository.delete(fileEntity);

        return true;
    }


}
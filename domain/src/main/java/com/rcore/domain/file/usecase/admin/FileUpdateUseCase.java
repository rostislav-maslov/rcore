package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.access.AdminFileUpdateAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;

import java.time.LocalDateTime;

public class FileUpdateUseCase extends FileAdminBaseUseCase {

    public FileUpdateUseCase(FileRepository fileRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(fileRepository, new AdminFileUpdateAccess(), authorizationByTokenUseCase);
    }

    public FileEntity update(FileEntity fileEntity) throws FileNotFoundException, AuthenticationException, AuthorizationException {
        checkAccess();

        FileEntity oldFile = fileRepository.findById(fileEntity.getId())
                .orElseThrow(() -> new FileNotFoundException());

        oldFile.setIsPrivate(fileEntity.getIsPrivate());
        oldFile.setFileName(fileEntity.getFileName());
        oldFile.setUpdatedAt(LocalDateTime.now());

        oldFile = fileRepository.save(fileEntity);
        return oldFile;
    }


}
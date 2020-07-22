package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.access.AdminFileCreateAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;

import java.io.InputStream;


public class FileCreateUseCase  extends FileAdminBaseUseCase {
    private final FileIdGenerator idGenerator;
    private final FileStorage fileStorage;

    public FileCreateUseCase(FileRepository fileRepository, FileIdGenerator idGenerator, FileStorage fileStorage, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(fileRepository, new AdminFileCreateAccess(), authorizationByTokenUseCase);
        this.idGenerator = idGenerator;
        this.fileStorage = fileStorage;
    }

    public FileEntity create(InputStream content, String fileName, String contentType, boolean isPrivate) throws AuthenticationException, AuthorizationException {
        checkAccess();

        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(idGenerator.generate());
        fileEntity.setFileName(fileName);
        fileEntity.setIsPrivate(isPrivate);
        fileEntity.setFilePath(fileStorage.store(content, fileName, contentType));

        fileEntity = fileRepository.save(fileEntity);
        return fileEntity;
    }


}
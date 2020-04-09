package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileStoreException;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.role.AdminFileCreateRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class FileCreateUseCase  extends FileAdminBaseUseCase {
    private final FileIdGenerator idGenerator;
    private final FileStorage fileStorage;

    public FileCreateUseCase(UserEntity actor, FileRepository fileRepository, FileIdGenerator idGenerator, FileStorage fileStorage) throws AuthorizationException {
        super(actor, fileRepository, new AdminFileCreateRole());
        this.idGenerator = idGenerator;
        this.fileStorage = fileStorage;
    }

    public FileEntity create(File file, boolean isPrivate) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(idGenerator.generate());
        fileEntity.setFileName(file.getName());
        fileEntity.setIsPrivate(isPrivate);

        fileEntity.setFilePath(fileStorage.store(file));

        return fileRepository.save(fileEntity);
    }


}
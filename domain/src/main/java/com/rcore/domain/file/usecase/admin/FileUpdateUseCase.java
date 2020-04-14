package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.role.AdminFileUpdateRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Date;

public class FileUpdateUseCase extends FileAdminBaseUseCase {

    public FileUpdateUseCase(UserEntity actor, FileRepository fileRepository) throws AuthorizationException {
        super(actor, fileRepository, new AdminFileUpdateRole());
    }

    public FileEntity update(FileEntity fileEntity) throws FileNotFoundException {
        FileEntity oldFile = fileRepository.findById(fileEntity.getId())
                .orElseThrow(() -> new FileNotFoundException());

        oldFile.setIsPrivate(fileEntity.getIsPrivate());
        oldFile.setFileName(fileEntity.getFileName());
        oldFile.setUpdatedAt(LocalDateTime.now());

        return fileRepository.save(fileEntity);
    }


}
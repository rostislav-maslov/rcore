package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.file.port.FileRepository;

class FileAdminBaseUseCase extends AdminUseCase {

    protected final FileRepository fileRepository;

    public FileAdminBaseUseCase(UserEntity actor, FileRepository fileRepository, Role accessRole) throws AuthorizationException {
        super(actor, accessRole);
        this.fileRepository = fileRepository;
    }

}

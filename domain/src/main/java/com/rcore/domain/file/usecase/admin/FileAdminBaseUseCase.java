package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.file.port.FileRepository;

class FileAdminBaseUseCase extends AdminUseCase {

    protected final FileRepository fileRepository;

    public FileAdminBaseUseCase(FileRepository fileRepository, Access accessAccess, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(accessAccess, authorizationByTokenUseCase);
        this.fileRepository = fileRepository;
    }

}

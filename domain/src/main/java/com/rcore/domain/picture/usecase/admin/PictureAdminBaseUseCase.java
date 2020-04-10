package com.rcore.domain.picture.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.picture.port.PictureRepository;

class PictureAdminBaseUseCase extends AdminUseCase {

    protected final PictureRepository pictureRepository;

    public PictureAdminBaseUseCase(UserEntity actor, PictureRepository pictureRepository, Role accessRole) throws AuthorizationException {
        super(actor, accessRole);
        this.pictureRepository = pictureRepository;
    }

}

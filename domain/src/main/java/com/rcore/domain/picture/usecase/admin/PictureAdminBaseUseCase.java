package com.rcore.domain.picture.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.picture.port.PictureRepository;

class PictureAdminBaseUseCase extends AdminUseCase {

    protected final PictureRepository pictureRepository;

    public PictureAdminBaseUseCase(UserEntity actor, PictureRepository pictureRepository, Access accessAccess) throws AuthorizationException {
        super(actor, accessAccess);
        this.pictureRepository = pictureRepository;
    }

}

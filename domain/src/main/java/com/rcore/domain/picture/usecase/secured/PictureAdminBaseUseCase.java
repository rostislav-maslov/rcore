package com.rcore.domain.picture.usecase.secured;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.picture.port.PictureRepository;

class PictureAdminBaseUseCase extends AdminUseCase {

    protected final PictureRepository pictureRepository;

    public PictureAdminBaseUseCase(PictureRepository pictureRepository, Access accessAccess, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(accessAccess, authorizationByTokenUseCase);
        this.pictureRepository = pictureRepository;
    }

}

package com.rcore.domain.picture.usecase.secured;

import com.rcore.domain.picture.access.PictureDeleteUnusedAccess;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;

public class PictureDeleteUnusedUseCase extends PictureAdminBaseUseCase {

    public PictureDeleteUnusedUseCase(PictureRepository pictureRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(pictureRepository, new PictureDeleteUnusedAccess(), authorizationByTokenUseCase);
    }

    public void deleteUnused() throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        pictureRepository.deleteUnused();
    }
}

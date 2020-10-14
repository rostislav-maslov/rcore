package com.rcore.domain.picture.usecase.secured;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.picture.access.AdminPictureDeleteAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;

public class PictureDeleteUseCase  extends PictureAdminBaseUseCase {

    private final PictureStorage pictureStorage;

    public PictureDeleteUseCase(PictureRepository pictureRepository, PictureStorage pictureStorage, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(pictureRepository, new AdminPictureDeleteAccess(), authorizationByTokenUseCase);
        this.pictureStorage = pictureStorage;
    }

    public Boolean delete(PictureEntity pictureEntity) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        if (pictureEntity.getFilePath() != null && pictureEntity.getFilePath().length() > 0)
            pictureStorage.remove(pictureEntity.getFilePath());

        pictureRepository.delete(pictureEntity);

        return true;
    }


}
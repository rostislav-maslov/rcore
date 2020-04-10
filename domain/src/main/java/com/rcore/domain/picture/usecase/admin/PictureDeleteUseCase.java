package com.rcore.domain.picture.usecase.admin;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.picture.role.AdminPictureDeleteRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class PictureDeleteUseCase  extends PictureAdminBaseUseCase {

    private final PictureStorage pictureStorage;

    public PictureDeleteUseCase(UserEntity actor, PictureRepository pictureRepository, PictureStorage pictureStorage) throws AuthorizationException {
        super(actor, pictureRepository, new AdminPictureDeleteRole());
        this.pictureStorage = pictureStorage;
    }

    public Boolean delete(PictureEntity pictureEntity){
        if (pictureEntity.getFilePath() != null && pictureEntity.getFilePath().length() > 0)
            pictureStorage.remove(pictureEntity.getFilePath());

        pictureRepository.delete(pictureEntity);

        return true;
    }


}
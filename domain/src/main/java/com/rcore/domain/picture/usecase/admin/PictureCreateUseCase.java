package com.rcore.domain.picture.usecase.admin;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.picture.role.AdminPictureCreateRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.io.File;


public class PictureCreateUseCase extends PictureAdminBaseUseCase {
    private final PictureIdGenerator idGenerator;
    private final PictureStorage pictureStorage;

    public PictureCreateUseCase(UserEntity actor, PictureRepository pictureRepository, PictureIdGenerator idGenerator, PictureStorage pictureStorage) throws AuthorizationException {
        super(actor, pictureRepository, new AdminPictureCreateRole());
        this.idGenerator = idGenerator;
        this.pictureStorage = pictureStorage;
    }

    public PictureEntity create(File file, boolean isPrivate) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setId(idGenerator.generate());
        pictureEntity.setIsPrivate(isPrivate);
        pictureEntity.setFileName(file.getName());
        pictureEntity.setFilePath(pictureStorage.store(file));

        return pictureRepository.save(pictureEntity);
    }


}
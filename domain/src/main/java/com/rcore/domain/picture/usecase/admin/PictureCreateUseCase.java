package com.rcore.domain.picture.usecase.admin;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.picture.access.AdminPictureCreateAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.io.InputStream;


public class PictureCreateUseCase extends PictureAdminBaseUseCase {
    private final PictureIdGenerator idGenerator;
    private final PictureStorage pictureStorage;

    public PictureCreateUseCase(UserEntity actor, PictureRepository pictureRepository, PictureIdGenerator idGenerator, PictureStorage pictureStorage) throws AuthorizationException {
        super(actor, pictureRepository, new AdminPictureCreateAccess());
        this.idGenerator = idGenerator;
        this.pictureStorage = pictureStorage;
    }

    public PictureEntity create(InputStream content, String fileName, String contentType, boolean isPrivate) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setId(idGenerator.generate());
        pictureEntity.setIsPrivate(isPrivate);
        pictureEntity.setFileName(fileName);
        pictureEntity.setFilePath(pictureStorage.store(content, fileName, contentType));

        return pictureRepository.save(pictureEntity);
    }


}
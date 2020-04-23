package com.rcore.domain.picture.usecase.admin;

import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.picture.access.AdminPictureViewAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;

import java.io.InputStream;
import java.util.Optional;

public class PictureViewUseCase extends PictureAdminBaseUseCase {

    private final PictureStorage pictureStorage;

    public PictureViewUseCase(PictureRepository pictureRepository, PictureStorage pictureStorage, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(pictureRepository, new AdminPictureViewAccess(), authorizationByTokenUseCase);
        this.pictureStorage = pictureStorage;
    }

    public Optional<PictureEntity> findById(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();

        return pictureRepository.findById(id);
    }

    public Optional<PictureEntity> search(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return pictureRepository.findById(id);
    }

    public SearchResult<PictureEntity> find(Long size, Long skip) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return pictureRepository.find(size, skip);
    }

    public Optional<InputStream> getInputStream(String id) throws FileNotFoundException, AuthenticationException, AuthorizationException {
        PictureEntity fileEntity = findById(id)
                .orElseThrow(() -> new FileNotFoundException());

        return pictureStorage.getInputStream(fileEntity.getFilePath());
    }

    public Optional<InputStream> getInputStreamByWidth(String id, Integer width) throws AuthenticationException, AuthorizationException {

        Optional<PictureEntity> pictureEntity = findById(id);

        Optional<String> filePath = pictureEntity.get().getSizes()
                .values()
                .stream()
                .filter(size -> size.getWidth().equals(width))
                .findFirst()
                .map(PictureEntity.Size::getFilePath);

        if (!filePath.isPresent())
            return Optional.empty();

        return pictureStorage.getInputStream(pictureEntity.get().getFilePath());
    }

}
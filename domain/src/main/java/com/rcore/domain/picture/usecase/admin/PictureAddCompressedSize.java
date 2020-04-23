package com.rcore.domain.picture.usecase.admin;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.access.AdminAddCompressedSizeAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;

public class PictureAddCompressedSize extends PictureAdminBaseUseCase {

    private final PictureCompressor pictureCompressor;

    public PictureAddCompressedSize(PictureRepository pictureRepository, PictureCompressor pictureCompressor, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(pictureRepository, new AdminAddCompressedSizeAccess(), authorizationByTokenUseCase);
        this.pictureCompressor = pictureCompressor;
    }

    public PictureEntity addCompressedSize(PictureEntity pictureEntity, Integer width, Double quality) {
        pictureEntity.addSize(pictureCompressor.compressImage(pictureEntity, width, quality));
        return pictureRepository.save(pictureEntity);
    }
}

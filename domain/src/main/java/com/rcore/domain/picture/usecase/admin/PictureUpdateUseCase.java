package com.rcore.domain.picture.usecase.admin;

import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.access.AdminPictureUpdateAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.time.LocalDateTime;

public class PictureUpdateUseCase  extends PictureAdminBaseUseCase {

    private final PictureCompressor pictureCompressor;

    public PictureUpdateUseCase(UserEntity actor, PictureRepository pictureRepository, PictureCompressor pictureCompressor)throws AuthorizationException {
        super(actor, pictureRepository, new AdminPictureUpdateAccess());
        this.pictureCompressor = pictureCompressor;
    }

    public PictureEntity update(PictureEntity pictureEntity) throws FileNotFoundException {
        PictureEntity oldPicture = pictureRepository.findById(pictureEntity.getId())
                .orElseThrow(() -> new FileNotFoundException());

        oldPicture.setIsPrivate(pictureEntity.getIsPrivate());
        oldPicture.setFileName(pictureEntity.getFileName());
        oldPicture.setUpdatedAt(LocalDateTime.now());

        return pictureRepository.save(pictureEntity);
    }

    public PictureEntity addCompressedSize(PictureEntity pictureEntity, Integer width, Double quality) {
        pictureEntity.addSize(pictureCompressor.compressImage(pictureEntity, width, quality));
        return pictureRepository.save(pictureEntity);
    }


}
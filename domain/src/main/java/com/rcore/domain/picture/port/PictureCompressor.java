package com.rcore.domain.picture.port;

import com.rcore.domain.picture.entity.PictureEntity;

public interface PictureCompressor {

    PictureEntity.Size compressImage(PictureEntity pictureEntity, Integer newWidth, Double quality);

}

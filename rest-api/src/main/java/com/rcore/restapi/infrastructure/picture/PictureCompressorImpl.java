package com.rcore.restapi.infrastructure.picture;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureCompressor;
import org.springframework.stereotype.Component;

//TODO реализовать
@Component
public class PictureCompressorImpl implements PictureCompressor {

    @Override
    public PictureEntity.Size compressImage(PictureEntity pictureEntity, Integer newWidth, Double quality) {
        return null;
    }
}

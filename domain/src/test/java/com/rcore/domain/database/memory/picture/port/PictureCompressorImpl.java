package com.rcore.domain.database.memory.picture.port;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureStorage;

import java.io.File;

public class PictureCompressorImpl implements PictureCompressor {

    private final PictureStorage pictureStorage;

    public PictureCompressorImpl(PictureStorage pictureStorage) {
        this.pictureStorage = pictureStorage;
    }

    @Override
    public PictureEntity.Size compressImage(PictureEntity pictureEntity, Integer newWidth, Double quality) {
        //как то получаем сжатое изображение
        File compressedFile = new File(pictureEntity.getFilePath());
        PictureEntity.Size size = new PictureEntity.Size();
        size.setWidth(newWidth);
        size.setFilePath(pictureStorage.store(compressedFile));
        return size;
    }
}

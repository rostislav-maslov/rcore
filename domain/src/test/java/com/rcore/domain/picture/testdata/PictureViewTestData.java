package com.rcore.domain.picture.testdata;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureIdGenerator;

public class PictureViewTestData {

    public static PictureEntity create(String filePath, PictureIdGenerator fileIdGenerator) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setId(fileIdGenerator.generate());
        pictureEntity.setFilePath(filePath);
        pictureEntity.addSize(PictureEntity.Size.builder()
                .filePath(filePath)
                .width(100)
                .build());

        return pictureEntity;
    }

}

package com.rcore.adapter.domain.picture.mapper;

import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.domain.picture.entity.PictureEntity;

public class PictureMapper implements ExampleDataMapper<PictureEntity, PictureDTO> {
    @Override
    public PictureDTO map(PictureEntity pictureEntity) {
        return PictureDTO.builder()
                .id(pictureEntity.getId())
                .createdAt(pictureEntity.getCreatedAt())
                .fileName(pictureEntity.getFileName())
                .filePath(pictureEntity.getFilePath())
                .isPrivate(pictureEntity.getIsPrivate())
                .sizes(pictureEntity.getSizes())
                .timeZone(pictureEntity.getTimeZone())
                .updatedAt(pictureEntity.getUpdatedAt())
                .build();
    }

    @Override
    public PictureEntity inverseMap(PictureDTO pictureDTO) {
        return PictureEntity.builder()
                .id(pictureDTO.getId())
                .createdAt(pictureDTO.getCreatedAt())
                .fileName(pictureDTO.getFileName())
                .filePath(pictureDTO.getFilePath())
                .isPrivate(pictureDTO.getIsPrivate())
                .sizes(pictureDTO.getSizes())
                .timeZone(pictureDTO.getTimeZone())
                .updatedAt(pictureDTO.getUpdatedAt())
                .build();
    }
}

package com.rcore.adapter.domain.file.mapper;

import com.rcore.adapter.domain.file.dto.FileDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.domain.file.entity.FileEntity;

public class FileMapper implements ExampleDataMapper<FileEntity, FileDTO> {

    @Override
    public FileDTO map(FileEntity entity) {
        return FileDTO.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .fileName(entity.getFileName())
                .filePath(entity.getFilePath())
                .isPrivate(entity.getIsPrivate())
                .timeZone(entity.getTimeZone())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public FileEntity inverseMap(FileDTO fileDTO) {
        return FileEntity.builder()
                .id(fileDTO.getId())
                .createdAt(fileDTO.getCreatedAt())
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .isPrivate(fileDTO.getIsPrivate())
                .timeZone(fileDTO.getTimeZone())
                .updatedAt(fileDTO.getUpdatedAt())
                .build();
    }
}

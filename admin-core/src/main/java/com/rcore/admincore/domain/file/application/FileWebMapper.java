package com.rcore.admincore.domain.file.application;

import com.rcore.adapter.domain.file.dto.FileDTO;
import com.rcore.admincore.domain.file.web.api.FileWeb;
import com.rcore.commons.mapper.ExampleDataMapper;
import org.springframework.stereotype.Component;

@Component
public class FileWebMapper implements ExampleDataMapper<FileDTO, FileWeb> {

    @Override
    public FileWeb map(FileDTO fileDTO) {
        return FileWeb.builder()
                .id(fileDTO.getId())
                .name(fileDTO.getFileName())
                .createdAt(fileDTO.getCreatedAt())
                .build();
    }

    @Override
    public FileDTO inverseMap(FileWeb fileWeb) {
        return FileDTO.builder()
                .id(fileWeb.getId())
                .fileName(fileWeb.getName())
                .createdAt(fileWeb.getCreatedAt())
                .build();
    }
}

package com.rcore.admincore.domain.picture.application;

import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.admincore.domain.picture.web.api.PictureWeb;
import com.rcore.commons.mapper.ExampleDataMapper;
import org.springframework.stereotype.Component;

@Component
public class PictureWebMapper implements ExampleDataMapper<PictureDTO, PictureWeb> {

    @Override
    public PictureWeb map(PictureDTO pictureDTO) {
        return PictureWeb.builder()
                .id(pictureDTO.getId())
                .name(pictureDTO.getFileName())
                .createdAt(pictureDTO.getCreatedAt())
                .build();
    }

    @Override
    public PictureDTO inverseMap(PictureWeb pictureWeb) {
        return PictureDTO.builder()
                .id(pictureWeb.getId())
                .fileName(pictureWeb.getName())
                .createdAt(pictureWeb.getCreatedAt())
                .build();
    }
}

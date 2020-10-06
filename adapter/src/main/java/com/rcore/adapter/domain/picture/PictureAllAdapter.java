package com.rcore.adapter.domain.picture;

import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.adapter.domain.picture.mapper.PictureMapper;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exception.PictureAccessException;
import com.rcore.domain.picture.exception.PictureNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
public class PictureAllAdapter {
    private PictureMapper pictureMapper = new PictureMapper();
    private final PictureConfig pictureConfig;

    public PictureDTO create(InputStream content, String fileName, String contentType) {
        return pictureMapper.map(pictureConfig.all.createUseCase().create(content, fileName, contentType, false));
    }

    public PictureDTO create(InputStream content, String fileName, String contentType, Boolean isUsed) {
        return pictureMapper.map(pictureConfig.all.createUseCase().create(content, fileName, contentType, isUsed));
    }

    public Optional<PictureDTO> findById(String id) throws PictureAccessException {
        return pictureConfig.all.viewUseCase().findById(id).map(pictureMapper::map);
    }

    public Optional<InputStream> getInputStream(String id) throws PictureAccessException, PictureNotFoundException {
        return pictureConfig.all.viewUseCase().getInputStream(id);
    }

    public Optional<InputStream> getInputStreamByWidth(String id, Integer width) throws PictureAccessException {
        return pictureConfig.all.viewUseCase().getInputStreamByWidth(id, width);
    }

    public Boolean delete(PictureEntity pictureEntity) {
        return pictureConfig.all.deleteUseCase().delete(pictureEntity);
    }

    public Boolean deleteById(String id) throws PictureNotFoundException {
        return pictureConfig.all.deleteUseCase().deleteById(id);
    }
}

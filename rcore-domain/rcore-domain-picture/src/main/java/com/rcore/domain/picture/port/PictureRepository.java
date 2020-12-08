package com.rcore.domain.picture.port;

import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.picture.entity.PictureEntity;

import java.util.Optional;

public interface PictureRepository extends CRUDRepository<String, PictureEntity, SearchFilters> {
    Optional<PictureEntity> findByPath(String path);
}

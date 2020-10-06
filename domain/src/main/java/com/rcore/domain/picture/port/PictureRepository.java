package com.rcore.domain.picture.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.picture.entity.PictureEntity;

public interface PictureRepository extends CRUDRepository<String, PictureEntity> {
    void deleteUnused();
}

package com.rcore.domain.picture.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureRepository;

public class FindPictureByIdUseCase extends AbstractFindByIdUseCase<String, PictureEntity, PictureRepository> {

    public FindPictureByIdUseCase(PictureRepository repository) {
        super(repository);
    }
}

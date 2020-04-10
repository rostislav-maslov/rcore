package com.rcore.domain.picture.usecase.all;

import com.rcore.domain.picture.port.PictureRepository;

class PictureBaseUseCase{

    protected final PictureRepository pictureRepository;

    public PictureBaseUseCase(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

}

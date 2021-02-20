package com.rcore.domain.picture.config;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.usecases.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PictureConfig {

    private final PictureRepository pictureRepository;
    private final PictureIdGenerator pictureIdGenerator;
    private final FileStorage fileStorage;

    public CreatePictureUseCase createPictureUseCase() {
        return new CreatePictureUseCase(pictureRepository, pictureIdGenerator, fileStorage);
    }

    public DeleteBufferPicturesUseCase deleteBufferPicturesUseCase() {
        return new DeleteBufferPicturesUseCase(findPictureByIdUseCase(), deletePictureUseCase());
    }

    public DeletePictureUseCase deletePictureUseCase() {
        return new DeletePictureUseCase(pictureRepository, fileStorage);
    }

    public FindPictureByIdUseCase findPictureByIdUseCase() {
        return new FindPictureByIdUseCase(pictureRepository);
    }

    public FindPictureByPathUseCase findPictureByPathUseCase() {
        return new FindPictureByPathUseCase(pictureRepository);
    }

}

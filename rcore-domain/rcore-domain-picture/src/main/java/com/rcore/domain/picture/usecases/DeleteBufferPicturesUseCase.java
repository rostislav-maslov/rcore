package com.rcore.domain.picture.usecases;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exceptions.PictureNotBufferException;
import com.rcore.domain.picture.exceptions.PictureNotFoundException;
import com.rcore.domain.picture.port.PictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class DeleteBufferPicturesUseCase extends UseCase<DeleteBufferPicturesUseCase.InputValues, DeleteBufferPicturesUseCase.OutputValues> {

    private final FindPictureByIdUseCase findPictureByIdUseCase;
    private final DeletePictureUseCase deletePictureUseCase;

    @Override
    public OutputValues execute(InputValues inputValues) {

        PictureEntity pictureEntity = findPictureByIdUseCase.execute(AbstractFindByIdUseCase.InputValues.of(inputValues.getId()))
                .getResult()
                .orElseThrow(() -> new PictureNotFoundException(inputValues.getId()));

        if (!pictureEntity.getIsBuffer())
            throw new PictureNotBufferException(inputValues.getId());

        deletePictureUseCase.execute(AbstractDeleteUseCase.InputValues.of(pictureEntity.getId()));

        return new OutputValues();
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String id;
    }

    public static class OutputValues implements UseCase.OutputValues {

    }

}

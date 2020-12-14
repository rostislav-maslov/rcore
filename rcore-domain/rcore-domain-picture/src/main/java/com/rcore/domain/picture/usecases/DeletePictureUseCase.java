package com.rcore.domain.picture.usecases;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exceptions.PictureNotFoundException;
import com.rcore.domain.picture.port.PictureRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePictureUseCase extends UseCase<IdInputValues<String>, AbstractDeleteUseCase.OutputValues> {

    private final PictureRepository repository;
    private final FileStorage fileStorage;

    @Override
    public AbstractDeleteUseCase.OutputValues execute(IdInputValues<String> stringIdInputValues) {
        PictureEntity pictureEntity = repository.findById(stringIdInputValues.getId())
                .orElseThrow(() -> new PictureNotFoundException(stringIdInputValues.getId()));

        fileStorage.remove(pictureEntity.getFilePath());

        //Если имеются разные размеры изображения то необходимо удалить эти файлы
        if (pictureEntity.getSizes() != null)
            pictureEntity.getSizes()
                    .forEach((key, size) -> fileStorage.remove(size.getFilePath()));

        return AbstractDeleteUseCase.OutputValues.of(repository.delete(pictureEntity.getId()));
    }
}

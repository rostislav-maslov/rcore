package com.rcore.domain.picture.usecases;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exceptions.PictureNotFoundException;
import com.rcore.domain.picture.port.PictureRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePictureUseCase extends UseCase<AbstractDeleteUseCase.InputValues<String>, AbstractDeleteUseCase.OutputValues> {

    private final PictureRepository repository;
    private final FileStorage fileStorage;

    @Override
    public AbstractDeleteUseCase.OutputValues execute(AbstractDeleteUseCase.InputValues<String> stringInputValues) {

        PictureEntity pictureEntity = repository.findById(stringInputValues.getId())
                .orElseThrow(() -> new PictureNotFoundException(stringInputValues.getId()));

        fileStorage.remove(pictureEntity.getFilePath());

        //Если имеются разные размеры изображения то необходимо удалить эти файлы
        if (pictureEntity.getSizes() != null)
            pictureEntity.getSizes()
                    .forEach((key, size) -> fileStorage.remove(size.getFilePath()));

        return AbstractDeleteUseCase.OutputValues.of(repository.delete(pictureEntity.getId()));
    }
}

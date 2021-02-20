package com.rcore.domain.file.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileRepository;

public class FindFileByIdUseCase extends AbstractFindByIdUseCase<String, FileEntity, FileRepository> {

    public FindFileByIdUseCase(FileRepository repository) {
        super(repository);
    }
}

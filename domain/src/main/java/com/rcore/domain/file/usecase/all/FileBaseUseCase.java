package com.rcore.domain.file.usecase.all;

import com.rcore.domain.file.port.FileRepository;

class FileBaseUseCase {

    protected final FileRepository fileRepository;

    public FileBaseUseCase(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

}

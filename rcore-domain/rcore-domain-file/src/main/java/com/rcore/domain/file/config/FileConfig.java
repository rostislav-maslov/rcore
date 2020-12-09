package com.rcore.domain.file.config;

import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.usecases.CreateFileUseCase;
import com.rcore.domain.file.usecases.DeleteFileUseCase;
import com.rcore.domain.file.usecases.FindFileByIdUseCase;
import com.rcore.domain.file.usecases.FindFileByPathUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileConfig {

    private final FileRepository fileRepository;
    private final FileIdGenerator fileIdGenerator;
    private final FileStorage fileStorage;

    public CreateFileUseCase createFileUseCase() {
        return new CreateFileUseCase(fileRepository, fileIdGenerator, fileStorage);
    }

    public DeleteFileUseCase deleteFileUseCase() {
        return new DeleteFileUseCase(fileRepository, fileStorage);
    }

    public FindFileByIdUseCase findFileByIdUseCase() {
        return new FindFileByIdUseCase(fileRepository);
    }

    public FindFileByPathUseCase findFileByPathUseCase() {
        return new FindFileByPathUseCase(fileRepository);
    }

}

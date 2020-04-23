package com.rcore.domain.file.config;

import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.usecase.admin.FileCreateUseCase;
import com.rcore.domain.file.usecase.admin.FileDeleteUseCase;
import com.rcore.domain.file.usecase.admin.FileUpdateUseCase;
import com.rcore.domain.file.usecase.admin.FileViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class FileConfig {

    @RequiredArgsConstructor
    public static class Admin {
        protected final FileRepository fileRepository;
        protected final FileIdGenerator idGenerator;
        protected final FileStorage fileStorage;
        protected final AuthorizationByTokenUseCase authorizationByTokenUseCase;

        public FileCreateUseCase createUseCase() throws AuthorizationException {
            return new FileCreateUseCase(this.fileRepository, idGenerator, fileStorage, authorizationByTokenUseCase);
        }

        public FileDeleteUseCase deleteUseCase() throws AuthorizationException {
            return new FileDeleteUseCase(this.fileRepository, fileStorage, authorizationByTokenUseCase);
        }

        public FileUpdateUseCase updateUseCase() throws AuthorizationException {
            return new FileUpdateUseCase(this.fileRepository, authorizationByTokenUseCase);
        }

        public FileViewUseCase viewUseCase() throws AuthorizationException {
            return new FileViewUseCase(this.fileRepository, fileStorage, authorizationByTokenUseCase);
        }
    }

    @RequiredArgsConstructor
    public static class All {

        protected final FileRepository fileRepository;
        protected final FileIdGenerator idGenerator;
        protected final FileStorage fileStorage;

        public com.rcore.domain.file.usecase.all.FileCreateUseCase createUseCase() {
            return new com.rcore.domain.file.usecase.all.FileCreateUseCase(fileRepository, idGenerator, fileStorage);
        }

        public com.rcore.domain.file.usecase.all.FileDeleteUseCase deleteUseCase() {
            return new com.rcore.domain.file.usecase.all.FileDeleteUseCase(fileRepository, fileStorage);
        }

        public com.rcore.domain.file.usecase.all.FileViewUseCase viewUseCase() {
            return new com.rcore.domain.file.usecase.all.FileViewUseCase(fileRepository, fileStorage);
        }

    }

    private final FileRepository fileRepository;
    private final FileIdGenerator idGenerator;
    private final FileStorage fileStorage;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    public final Admin admin;
    public final All all;

    public FileConfig(
            FileRepository fileRepository,
            FileIdGenerator idGenerator,
            FileStorage fileStorage, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        this.fileRepository = fileRepository;
        this.idGenerator = idGenerator;
        this.fileStorage = fileStorage;
        this.authorizationByTokenUseCase = authorizationByTokenUseCase;

        this.admin = new Admin(this.fileRepository, this.idGenerator, this.fileStorage, this.authorizationByTokenUseCase);
        this.all = new All(this.fileRepository, this.idGenerator, this.fileStorage);
    }

}

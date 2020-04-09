package com.rcore.domain.file.config;

import com.rcore.domain.base.port.BaseIdGenerator;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.usecase.admin.FileCreateUseCase;
import com.rcore.domain.file.usecase.admin.FileDeleteUseCase;
import com.rcore.domain.file.usecase.admin.FileUpdateUseCase;
import com.rcore.domain.file.usecase.admin.FileViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import lombok.Getter;

public class FileConfig {

    @Getter
    public static class Admin {
        protected final FileRepository fileRepository;
        protected final FileIdGenerator idGenerator;
        protected final FileStorage fileStorage;

        public Admin(FileRepository fileRepository, FileIdGenerator idGenerator, FileStorage fileStorage) {
            this.fileRepository = fileRepository;
            this.idGenerator = idGenerator;
            this.fileStorage = fileStorage;
        }

        public FileCreateUseCase createUseCase(UserEntity actor) throws AuthorizationException {
            return new FileCreateUseCase(actor, this.fileRepository, idGenerator, fileStorage);
        }

        public FileDeleteUseCase deleteUseCase(UserEntity actor) throws AuthorizationException {
            return new FileDeleteUseCase(actor, this.fileRepository, fileStorage);
        }

        public FileUpdateUseCase updateUseCase(UserEntity actor) throws AuthorizationException {
            return new FileUpdateUseCase(actor, this.fileRepository);
        }

        public FileViewUseCase viewUseCase(UserEntity actor) throws AuthorizationException {
            return new FileViewUseCase(actor, this.fileRepository, fileStorage);
        }
    }

    public static class All {

        protected final FileRepository fileRepository;
        protected final FileIdGenerator idGenerator;
        protected final FileStorage fileStorage;

        public All(FileRepository fileRepository, FileIdGenerator idGenerator, FileStorage fileStorage) {
            this.fileRepository = fileRepository;
            this.idGenerator = idGenerator;
            this.fileStorage = fileStorage;
        }

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

    public final Admin admin;
    public final All all;

    public FileConfig(
            FileRepository fileRepository,
            FileIdGenerator idGenerator,
            FileStorage fileStorage) {
        this.fileRepository = fileRepository;
        this.idGenerator = idGenerator;
        this.fileStorage = fileStorage;

        this.admin = new Admin(this.fileRepository, this.idGenerator, this.fileStorage);
        this.all = new All(this.fileRepository, this.idGenerator, this.fileStorage);
    }

}

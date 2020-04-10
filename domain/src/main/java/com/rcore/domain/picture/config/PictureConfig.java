package com.rcore.domain.picture.config;

import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.picture.usecase.admin.*;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import lombok.Getter;

public class PictureConfig {

    @Getter
    public static class Admin {
        protected final PictureRepository pictureRepository;
        protected final PictureIdGenerator pictureIdGenerator;
        protected final PictureStorage pictureStorage;
        protected final PictureCompressor pictureCompressor;

        public Admin(PictureRepository pictureRepository, PictureIdGenerator pictureIdGenerator, PictureStorage pictureStorage, PictureCompressor pictureCompressor) {
            this.pictureRepository = pictureRepository;
            this.pictureIdGenerator = pictureIdGenerator;
            this.pictureStorage = pictureStorage;
            this.pictureCompressor = pictureCompressor;
        }

        public PictureCreateUseCase createUseCase(UserEntity actor) throws AuthorizationException {
            return new PictureCreateUseCase(actor, this.pictureRepository, pictureIdGenerator, pictureStorage);
        }

        public PictureDeleteUseCase deleteUseCase(UserEntity actor) throws AuthorizationException {
            return new PictureDeleteUseCase(actor, this.pictureRepository, pictureStorage);
        }

        public PictureUpdateUseCase updateUseCase(UserEntity actor) throws AuthorizationException {
            return new PictureUpdateUseCase(actor, this.pictureRepository, pictureCompressor);
        }

        public PictureViewUseCase viewUseCase(UserEntity actor) throws AuthorizationException {
            return new PictureViewUseCase(actor, this.pictureRepository, pictureStorage);
        }

        public PictureAddCompressedSize addCompressedSizeUseCase(UserEntity actor) throws AuthorizationException {
            return new PictureAddCompressedSize(actor, this.pictureRepository, this.pictureCompressor);
        }
    }

    @Getter
    public static class All {

        protected final PictureRepository pictureRepository;
        protected final PictureIdGenerator pictureIdGenerator;
        protected final PictureStorage pictureStorage;
        protected final PictureCompressor pictureCompressor;

        public All(PictureRepository pictureRepository, PictureIdGenerator pictureIdGenerator, PictureStorage pictureStorage, PictureCompressor pictureCompressor) {
            this.pictureRepository = pictureRepository;
            this.pictureIdGenerator = pictureIdGenerator;
            this.pictureStorage = pictureStorage;
            this.pictureCompressor = pictureCompressor;
        }

        public com.rcore.domain.picture.usecase.all.PictureCreateUseCase createUseCase() {
            return new com.rcore.domain.picture.usecase.all.PictureCreateUseCase(this.pictureRepository, this.pictureIdGenerator, this.pictureStorage);
        }

        public com.rcore.domain.picture.usecase.all.PictureDeleteUseCase deleteUseCase() {
            return new com.rcore.domain.picture.usecase.all.PictureDeleteUseCase(this.pictureRepository, this.pictureStorage);
        }

        public com.rcore.domain.picture.usecase.all.PictureViewUseCase viewUseCase() {
            return new com.rcore.domain.picture.usecase.all.PictureViewUseCase(this.pictureRepository, this.pictureStorage);
        }
    }

    private final PictureRepository pictureRepository;
    private final PictureIdGenerator idGenerator;
    protected final PictureStorage pictureStorage;
    protected final PictureCompressor pictureCompressor;

    public final Admin admin;
    public final All all;

    public PictureConfig(PictureRepository pictureRepository, PictureIdGenerator idGenerator, PictureStorage pictureStorage, PictureCompressor pictureCompressor) {
        this.pictureRepository = pictureRepository;
        this.idGenerator = idGenerator;
        this.pictureStorage = pictureStorage;
        this.pictureCompressor = pictureCompressor;

        this.admin = new Admin(this.pictureRepository, this.idGenerator, this.pictureStorage, this.pictureCompressor);
        this.all = new All(this.pictureRepository, this.idGenerator, this.pictureStorage, this.pictureCompressor);
    }

}

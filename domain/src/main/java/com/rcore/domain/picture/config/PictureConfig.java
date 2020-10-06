package com.rcore.domain.picture.config;

import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.picture.usecase.secured.*;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PictureConfig {

    @Getter
    @RequiredArgsConstructor
    public static class Admin {
        protected final PictureRepository pictureRepository;
        protected final PictureIdGenerator pictureIdGenerator;
        protected final PictureStorage pictureStorage;
        protected final PictureCompressor pictureCompressor;
        protected final AuthorizationByTokenUseCase authorizationByTokenUseCase;

        public PictureCreateUseCase createUseCase() throws AuthorizationException {
            return new PictureCreateUseCase(this.pictureRepository, pictureIdGenerator, pictureStorage, authorizationByTokenUseCase);
        }

        public PictureDeleteUseCase deleteUseCase() throws AuthorizationException {
            return new PictureDeleteUseCase(this.pictureRepository, pictureStorage, authorizationByTokenUseCase);
        }

        public PictureUpdateUseCase updateUseCase() throws AuthorizationException {
            return new PictureUpdateUseCase(this.pictureRepository, pictureCompressor, authorizationByTokenUseCase);
        }

        public PictureViewUseCase viewUseCase() throws AuthorizationException {
            return new PictureViewUseCase(this.pictureRepository, pictureStorage, authorizationByTokenUseCase);
        }

        public PictureAddCompressedSize addCompressedSizeUseCase() throws AuthorizationException {
            return new PictureAddCompressedSize(this.pictureRepository, this.pictureCompressor, authorizationByTokenUseCase);
        }

        public ChangeUsedPictureUseCase changeUsedPictureUseCase() throws AuthorizationException {
            return new ChangeUsedPictureUseCase(this.pictureRepository, this.authorizationByTokenUseCase);
        }

        public PictureDeleteUnusedUseCase deleteUnusedUseCase() throws AuthorizationException {
            return new PictureDeleteUnusedUseCase(this.pictureRepository, this.authorizationByTokenUseCase);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class All {

        protected final PictureRepository pictureRepository;
        protected final PictureIdGenerator pictureIdGenerator;
        protected final PictureStorage pictureStorage;
        protected final PictureCompressor pictureCompressor;

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
    protected final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    public final Admin admin;
    public final All all;

    public PictureConfig(PictureRepository pictureRepository, PictureIdGenerator idGenerator, PictureStorage pictureStorage, PictureCompressor pictureCompressor, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        this.pictureRepository = pictureRepository;
        this.idGenerator = idGenerator;
        this.pictureStorage = pictureStorage;
        this.pictureCompressor = pictureCompressor;
        this.authorizationByTokenUseCase = authorizationByTokenUseCase;

        this.admin = new Admin(this.pictureRepository, this.idGenerator, this.pictureStorage, this.pictureCompressor, this.authorizationByTokenUseCase);
        this.all = new All(this.pictureRepository, this.idGenerator, this.pictureStorage, this.pictureCompressor);
    }

}

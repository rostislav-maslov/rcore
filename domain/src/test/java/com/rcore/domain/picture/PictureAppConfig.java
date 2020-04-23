package com.rcore.domain.picture;

import com.rcore.domain.database.memory.picture.port.PictureCompressorImpl;
import com.rcore.domain.database.memory.picture.port.PictureIdGeneratorImpl;
import com.rcore.domain.database.memory.picture.port.PictureRepositoryImpl;
import com.rcore.domain.database.memory.picture.port.PictureStorageImpl;
import com.rcore.domain.database.memory.token.port.AccessTokenStorageImpl;
import com.rcore.domain.database.memory.token.port.RefreshTokenRepositoryImpl;
import com.rcore.domain.database.memory.user.port.UserRepositoryImpl;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.port.UserRepository;
import lombok.Getter;

@Getter
public class PictureAppConfig {

    private final String FILE_PATH = "src/test/java/com/rcore/resources/file.jpg";

    private final PictureConfig pictureConfig;
    private final PictureRepository pictureRepository;
    private final PictureIdGenerator pictureIdGenerator;
    private final PictureStorage pictureStorage;
    private final PictureCompressor pictureCompressor;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenStorage accessTokenStorage;
    private final UserRepository userRepository;

    public PictureAppConfig() {
        this.refreshTokenRepository = new RefreshTokenRepositoryImpl();
        this.accessTokenStorage= new AccessTokenStorageImpl();
        this.userRepository = new UserRepositoryImpl();
        this.pictureRepository = new PictureRepositoryImpl();
        this.pictureIdGenerator = new PictureIdGeneratorImpl();
        this.pictureStorage = new PictureStorageImpl();
        this.pictureCompressor = new PictureCompressorImpl(pictureStorage);
        this.authorizationByTokenUseCase = new AuthorizationByTokenUseCase(this.refreshTokenRepository, this.accessTokenStorage, this.userRepository);

        this.pictureConfig = new PictureConfig(this.pictureRepository, this.pictureIdGenerator, this.pictureStorage, this.pictureCompressor, authorizationByTokenUseCase);
    }
}

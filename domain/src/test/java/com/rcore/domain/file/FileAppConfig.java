package com.rcore.domain.file;

import com.rcore.domain.database.memory.file.port.FileIdGeneratorImpl;
import com.rcore.domain.database.memory.file.port.FileRepositoryImpl;
import com.rcore.domain.database.memory.file.port.FileStorageImpl;
import com.rcore.domain.database.memory.token.port.AccessTokenStorageImpl;
import com.rcore.domain.database.memory.token.port.RefreshTokenRepositoryImpl;
import com.rcore.domain.database.memory.token.port.RefreshTokenStorageImpl;
import com.rcore.domain.database.memory.user.port.UserRepositoryImpl;
import com.rcore.domain.file.config.FileConfig;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.port.UserRepository;
import lombok.Getter;

@Getter
public class FileAppConfig {

    private final String FILE_PATH = "src/test/java/com/rcore/resources/file.jpg";

    private final FileConfig fileConfig;
    private final FileIdGenerator idGenerator;
    private final FileRepository fileRepository;
    private final FileStorage fileStorage;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenStorage accessTokenStorage;
    private final RefreshTokenStorage refreshTokenStorage;
    private final UserRepository userRepository;

    public FileAppConfig() {
        this.idGenerator = new FileIdGeneratorImpl();
        this.fileRepository = new FileRepositoryImpl();
        this.fileStorage = new FileStorageImpl();
        this.refreshTokenRepository = new RefreshTokenRepositoryImpl();
        this.accessTokenStorage = new AccessTokenStorageImpl();
        this.refreshTokenStorage = new RefreshTokenStorageImpl();
        this.userRepository = new UserRepositoryImpl();

        this.authorizationByTokenUseCase = new AuthorizationByTokenUseCase(this.accessTokenStorage, refreshTokenStorage, this.userRepository);

        this.fileConfig = new FileConfig(fileRepository, idGenerator, fileStorage, this.authorizationByTokenUseCase);
    }
}

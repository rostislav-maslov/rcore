package com.rcore.database.mongo.config;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.database.mongo.token.port.AccessTokenIdGeneratorImpl;
import com.rcore.database.mongo.token.port.RefreshTokenIdGeneratorImpl;
import com.rcore.database.mongo.token.port.RefreshTokenRepositoryImpl;
import com.rcore.database.mongo.user.port.UserRepositoryImpl;
import com.rcore.database.mongo.user.port.model.UserDoc;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.port.IdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.port.impl.PasswordGeneratorImpl;
import lombok.Getter;

@Getter
public class TestAppConfig {

    private final DatabaseConfig databaseConfig;
    private final UserConfig userConfig;
    private final UserRepository<UserDoc> userRepository;
    private final IdGenerator idGenerator;
    private final PasswordGenerator passwordGenerator;
    private final ExpireTokenUseCase expireTokenUseCase;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final RefreshTokenRepository<RefreshTokenEntity> refreshTokenRepository;

    public TestAppConfig() throws Exception {
        this.databaseConfig = new DatabaseConfig();
        this.userRepository = new UserRepositoryImpl(databaseConfig.getMongoTemplate());
        this.idGenerator = new ObjectIdGenerator();
        this.passwordGenerator = new PasswordGeneratorImpl();
        this.refreshTokenRepository = new RefreshTokenRepositoryImpl(databaseConfig.getMongoTemplate());
        this.expireTokenUseCase = new ExpireTokenUseCase(refreshTokenRepository);
        this.createRefreshTokenUseCase = new CreateRefreshTokenUseCase(new RefreshTokenIdGeneratorImpl(), refreshTokenRepository, new TokenSaltGeneratorImpl());
        this.createAccessTokenUseCase = new CreateAccessTokenUseCase(new AccessTokenIdGeneratorImpl(), createRefreshTokenUseCase);


        this.userConfig = new UserConfig(userRepository, idGenerator, passwordGenerator, expireTokenUseCase, createRefreshTokenUseCase, createAccessTokenUseCase, refreshTokenRepository);
    }

}

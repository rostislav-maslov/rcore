package com.rcore.database.mongo.config;

import com.rcore.database.mongo.domain.role.port.RoleRepositoryImpl;
import com.rcore.database.mongo.domain.token.port.AccessTokenIdGeneratorImpl;
import com.rcore.database.mongo.domain.token.port.RefreshTokenIdGeneratorImpl;
import com.rcore.database.mongo.domain.token.port.RefreshTokenRepositoryImpl;
import com.rcore.database.mongo.domain.user.port.UserIdGeneratorImpl;
import com.rcore.database.mongo.domain.user.port.UserRepositoryImpl;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.port.impl.PasswordGeneratorImpl;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Getter
@Component
public class TestAppConfig {

    private final DatabaseConfig databaseConfig;
    private final UserConfig userConfig;
    private final UserRepository userRepository;
    private final UserIdGenerator userIdGenerator;
    private final PasswordGenerator passwordGenerator;
    private final ExpireTokenUseCase expireTokenUseCase;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RoleRepository roleRepository;
    private final AccessTokenStorage accessTokenStorage;
    private final RefreshTokenStorage refreshTokenStorage;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    public TestAppConfig() throws Exception {
        this.databaseConfig = new DatabaseConfig();
        this.userRepository = new UserRepositoryImpl(databaseConfig.getMongoTemplate());
        this.userIdGenerator = new UserIdGeneratorImpl();
        this.passwordGenerator = new PasswordGeneratorImpl();
        this.refreshTokenRepository = new RefreshTokenRepositoryImpl(databaseConfig.getMongoTemplate());
        this.accessTokenStorage = new AccessTokenStorage() {
            @Override
            public Optional<AccessTokenEntity> current() {
                return Optional.empty();
            }

            @Override
            public void put(AccessTokenEntity accessTokenEntity) {

            }

            @Override
            public Optional<AccessTokenEntity> findById(String id) {
                return Optional.empty();
            }

            @Override
            public void expireAccessToken(AccessTokenEntity accessTokenEntity) {

            }

            @Override
            public void expireAllAccessTokenByRefreshTokenId(String refreshTokenId) {

            }

            @Override
            public void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId) {

            }
        };
        this.refreshTokenStorage = new RefreshTokenStorage() {
            @Override
            public void put(RefreshTokenEntity refreshTokenEntity) {

            }

            @Override
            public Optional<RefreshTokenEntity> findById(String id) {
                return Optional.empty();
            }

            @Override
            public void expireRefreshToken(RefreshTokenEntity refreshTokenEntity) {

            }

            @Override
            public List<RefreshTokenEntity> findAllActiveByUserId(String userId) {
                return null;
            }
        };
        this.expireTokenUseCase = new ExpireTokenUseCase(refreshTokenStorage, accessTokenStorage);

        this.authorizationByTokenUseCase = new AuthorizationByTokenUseCase(this.accessTokenStorage,this.refreshTokenStorage, this.userRepository);
        this.createRefreshTokenUseCase = new CreateRefreshTokenUseCase(new RefreshTokenIdGeneratorImpl(), this.refreshTokenStorage, new TokenSaltGeneratorImpl());
        this.createAccessTokenUseCase = new CreateAccessTokenUseCase(new AccessTokenIdGeneratorImpl(), this.accessTokenStorage, createRefreshTokenUseCase);
        this.roleRepository = new RoleRepositoryImpl(databaseConfig.getMongoTemplate());

        this.userConfig = new UserConfig(userRepository, userIdGenerator, passwordGenerator, expireTokenUseCase, createRefreshTokenUseCase, createAccessTokenUseCase, this.roleRepository, authorizationByTokenUseCase, refreshTokenStorage, accessTokenStorage);
    }

}

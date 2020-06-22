package com.rcore.restapi.config;

import com.rcore.adapter.domain.file.FileAdapter;
import com.rcore.adapter.domain.picture.PictureAdapter;
import com.rcore.adapter.domain.role.RoleAdapter;
import com.rcore.adapter.domain.token.TokenAdapter;
import com.rcore.adapter.domain.user.UserAdapter;
import com.rcore.domain.file.config.FileConfig;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.role.config.RoleConfig;
import com.rcore.domain.role.port.RoleIdGenerator;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.token.config.TokenConfig;
import com.rcore.domain.token.port.*;
import com.rcore.domain.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.port.impl.PasswordGeneratorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * В данном конфиге создаем бины всех адаптеров
 */
@EnableWebMvc
@RequiredArgsConstructor
@Configuration
public class RestApiConfig {

    private final PictureRepository pictureRepository;
    private final PictureIdGenerator pictureIdGenerator;
    private final PictureStorage pictureStorage;
    private final PictureCompressor pictureCompressor;

    private final FileRepository fileRepository;
    private final FileIdGenerator fileIdGenerator;
    private final FileStorage fileStorage;

    private final UserRepository userRepository;
    private final UserIdGenerator userIdGenerator;
    private final PasswordGenerator passwordGenerator = new PasswordGeneratorImpl();

    private final RoleRepository roleRepository;
    private final RoleIdGenerator roleIdGenerator;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenStorage accessTokenStorage;
    private final RefreshTokenStorage refreshTokenStorage;
    private final AccessTokenIdGenerator accessTokenIdGenerator;
    private final RefreshTokenIdGenerator refreshTokenIdGenerator;
    private final TokenSaltGeneratorImpl tokenSaltGenerator = new TokenSaltGeneratorImpl();

    @Bean
    public PictureAdapter pictureAdapter() {
        return new PictureAdapter(new PictureConfig(pictureRepository, pictureIdGenerator, pictureStorage, pictureCompressor, new AuthorizationByTokenUseCase(accessTokenStorage, refreshTokenStorage, userRepository)));
    }

    @Bean
    public FileAdapter fileAdapter() {
        return new FileAdapter(new FileConfig(fileRepository, fileIdGenerator, fileStorage, new AuthorizationByTokenUseCase(accessTokenStorage, refreshTokenStorage, userRepository)));
    }

    @Bean
    public TokenAdapter tokenAdapter() {
        return new TokenAdapter(new TokenConfig(refreshTokenRepository, accessTokenStorage, refreshTokenStorage, userRepository, accessTokenIdGenerator, refreshTokenIdGenerator, tokenSaltGenerator));
    }

    @Bean
    public UserAdapter userAdapter() {
        return new UserAdapter(new UserConfig(
                userRepository,
                userIdGenerator,
                passwordGenerator,
                new ExpireTokenUseCase(refreshTokenRepository),
                new CreateRefreshTokenUseCase(refreshTokenIdGenerator, refreshTokenStorage, tokenSaltGenerator),
                new CreateAccessTokenUseCase(accessTokenIdGenerator, accessTokenStorage, new CreateRefreshTokenUseCase(refreshTokenIdGenerator, refreshTokenStorage, tokenSaltGenerator)),
                refreshTokenRepository,
                roleRepository,
                new AuthorizationByTokenUseCase(accessTokenStorage, refreshTokenStorage, userRepository),
                accessTokenStorage)
        );
    }

    @Bean
    public RoleAdapter roleAdapter() {
        return new RoleAdapter(new RoleConfig(roleRepository, roleIdGenerator, new AuthorizationByTokenUseCase(accessTokenStorage, refreshTokenStorage, userRepository)));
    }

}

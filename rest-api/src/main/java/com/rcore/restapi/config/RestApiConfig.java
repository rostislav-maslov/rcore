package com.rcore.restapi.config;

import com.rcore.adapter.domain.file.FileAdapter;
import com.rcore.adapter.domain.file.FileAllAdapter;
import com.rcore.adapter.domain.picture.PictureAdapter;
import com.rcore.adapter.domain.picture.PictureAllAdapter;
import com.rcore.adapter.domain.token.TokenAdapter;
import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
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
import com.rcore.domain.token.config.TokenConfig;
import com.rcore.domain.token.port.AccessTokenIdGenerator;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.RefreshTokenIdGenerator;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.port.impl.PasswordGeneratorImpl;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.jwt.converter.impl.JWTByAccessTokenGenerator;
import com.rcore.security.infrastructure.jwt.converter.impl.JWTByRefreshTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
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
    private PasswordGenerator passwordGenerator;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenStorage accessTokenStorage;
    private final AccessTokenIdGenerator accessTokenIdGenerator;
    private final RefreshTokenIdGenerator refreshTokenIdGenerator;
    private final TokenSaltGeneratorImpl tokenSaltGenerator = new TokenSaltGeneratorImpl();

    @Bean
    public PasswordGenerator passwordGenerator() {
        this.passwordGenerator = new PasswordGeneratorImpl();
        return this.passwordGenerator;
    }

    @Bean
    public PictureAdapter pictureAdapter() {
        return new PictureAdapter(new PictureConfig(pictureRepository, pictureIdGenerator, pictureStorage, pictureCompressor));
    }

    @Bean
    public FileAdapter fileAdapter() {
        return new FileAdapter(new FileConfig(fileRepository, fileIdGenerator, fileStorage));
    }

    @Bean
    public TokenAdapter tokenAdapter() {
        return new TokenAdapter(new TokenConfig(refreshTokenRepository, accessTokenStorage, userRepository, accessTokenIdGenerator, refreshTokenIdGenerator, tokenSaltGenerator));
    }

    @Bean
    public UserAdapter userAdapter() {
        return new UserAdapter(new UserConfig(
                userRepository, userIdGenerator, passwordGenerator,
                new ExpireTokenUseCase(refreshTokenRepository),
                new CreateRefreshTokenUseCase(refreshTokenIdGenerator, refreshTokenRepository, tokenSaltGenerator),
                new CreateAccessTokenUseCase(accessTokenIdGenerator, new CreateRefreshTokenUseCase(refreshTokenIdGenerator, refreshTokenRepository, tokenSaltGenerator)),
                refreshTokenRepository)
        );
    }

}

package com.rcore.domain.user.usecase.all;

import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.entity.TokenPair;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenIsExpiredException;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.AuthenticationPort;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.exception.InvalidEmailException;
import com.rcore.domain.user.exception.InvalidPasswordException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;

import java.time.LocalDateTime;

public class AuthenticationUseCase implements AuthenticationPort {
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final RefreshTokenStorage refreshTokenStorage;
    private final AccessTokenStorage accessTokenStorage;

    public AuthenticationUseCase(
            UserRepository userRepository,
            PasswordGenerator passwordGenerator,
            CreateRefreshTokenUseCase createRefreshTokenUseCase,
            CreateAccessTokenUseCase createAccessTokenUseCase,
            RefreshTokenStorage refreshTokenStorage,
            AccessTokenStorage accessTokenStorage) {
        this.userRepository = userRepository;
        this.passwordGenerator = passwordGenerator;
        this.createRefreshTokenUseCase = createRefreshTokenUseCase;
        this.createAccessTokenUseCase = createAccessTokenUseCase;
        this.refreshTokenStorage = refreshTokenStorage;
        this.accessTokenStorage = accessTokenStorage;
    }


    @Override
    public TokenPair authentication(String email, String password) throws UserBlockedException, InvalidPasswordException, InvalidEmailException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(InvalidEmailException::new);

        if (!passwordGenerator.check(userEntity.getId(), password, userEntity.getPassword())) {

            userEntity.setLastFailDate(LocalDateTime.now());
            userEntity.setFails(userEntity.getFails() + 1);
            userRepository.save(userEntity);

            throw new InvalidPasswordException();
        }

        if (!userEntity.getStatus().equals(UserStatus.ACTIVE)) {

            userEntity.setLastFailDate(LocalDateTime.now());
            userEntity.setFails(userEntity.getFails() + 1);
            userRepository.save(userEntity);

            throw new UserBlockedException();
        }

        userEntity.setFails(0);
        userEntity = userRepository.save(userEntity);

        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.create(userEntity);
        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.create(userEntity, refreshTokenEntity);

        return TokenPair.builder()
                .accessToken(accessTokenEntity)
                .refreshToken(refreshTokenEntity)
                .build();
    }

    @Override
    public TokenPair getNewTokenPairByRefreshToken(RefreshTokenEntity refreshTokenEntity) throws UserNotFoundException, UserBlockedException, AuthenticationException, RefreshTokenIsExpiredException {
        UserEntity userEntity = userRepository.findById(refreshTokenEntity.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if (!userEntity.getStatus().equals(UserStatus.ACTIVE))
            throw new UserBlockedException();


        RefreshTokenEntity fromRepo = refreshTokenStorage.findById(refreshTokenEntity.getId())
                .orElseThrow(() -> new AuthenticationException());

        if (!fromRepo.isActive()) {
            refreshTokenStorage.expireRefreshToken(fromRepo);
            accessTokenStorage.deactivateAllAccessTokenByRefreshTokenId(fromRepo.getId());
            throw new RefreshTokenIsExpiredException();
        }
            //expire

        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.create(userEntity, refreshTokenEntity);

        return TokenPair.builder()
                .accessToken(accessTokenEntity)
                .refreshToken(refreshTokenEntity)
                .build();
    }

    @Override
    public TokenPair getNewTokenPair(TokenPair tokenPair) throws UserNotFoundException, UserBlockedException, AuthenticationException, RefreshTokenIsExpiredException {
        UserEntity userEntity = userRepository.findById(tokenPair.getRefreshToken().getUserId())
                .orElseThrow(UserNotFoundException::new);
        if (!userEntity.getStatus().equals(UserStatus.ACTIVE))
            throw new UserBlockedException();

        //Проверка существование accessToken
        AccessTokenEntity accessTokenEntity = accessTokenStorage.findById(tokenPair.getAccessToken().getId())
                .orElseThrow(AuthenticationException::new);
        //Проверка существования рефреш токена
        RefreshTokenEntity refreshTokenEntity = refreshTokenStorage.findById(tokenPair.getRefreshToken().getId())
                .orElseThrow(AuthenticationException::new);

        //Проверка рефреш токена на его принадлежность к аксессу
        if (!refreshTokenEntity.getId().equals(accessTokenEntity.getCreateFromRefreshTokenId()))
            throw new RefreshTokenIsExpiredException();

        //Проверка статуса аксесса
        if (!accessTokenEntity.readyForRefresh())
            throw new RefreshTokenIsExpiredException();
        //Проверка статуса рефреша
        if (!refreshTokenEntity.isActive()) {
            refreshTokenStorage.expireRefreshToken(refreshTokenEntity);
            accessTokenStorage.deactivateAllAccessTokenByRefreshTokenId(refreshTokenEntity.getId());
            throw new RefreshTokenIsExpiredException();
        }

        //Деактивируем старый access
        accessTokenStorage.assignedRefreshedStatusToAccessToken(accessTokenEntity);
        //Создаём новый access
        AccessTokenEntity access = createAccessTokenUseCase.create(userEntity, refreshTokenEntity);

        return TokenPair.builder()
                .accessToken(access)
                .refreshToken(refreshTokenEntity)
                .build();
    }
}

package com.rcore.domain.token.port;

import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.entity.TokenPair;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenCreationException;
import com.rcore.domain.token.exception.RefreshTokenIsExpiredException;
import com.rcore.domain.user.exception.InvalidEmailException;
import com.rcore.domain.user.exception.InvalidPasswordException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotFoundException;

/**
 * аутентификация - подтверждение пользователя и выдача ему двух токенов доступа
 */
public interface AuthenticationPort {

    TokenPair authentication(String key, String token) throws UserNotFoundException, AuthenticationException, UserBlockedException, RefreshTokenCreationException, InvalidPasswordException, InvalidEmailException;
    TokenPair getNewTokenPairByRefreshToken(RefreshTokenEntity refreshTokenEntity) throws Throwable;
    TokenPair getNewTokenPair(TokenPair tokenPair) throws UserNotFoundException, UserBlockedException, AuthenticationException, RefreshTokenIsExpiredException;

}

package com.rcore.domain.token.port;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.user.exception.TokenExpiredException;

import java.util.Set;

/**
 * авторизация - проверка доступа пользователя к информации по его аксесс токену
 */
public interface AuthorizationPort {

    public Boolean checkAccess(AccessTokenEntity accessToken, Set<Access> userAccesses) throws TokenExpiredException;

}

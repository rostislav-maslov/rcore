package com.rcore.domain.token.port;

import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.entity.AccessTokenEntity;

import java.util.Set;

/**
 * авторизация - проверка доступа пользователя к информации по его аксесс токену
 */
public interface AuthorizationPort {

    public Boolean checkAccess(AccessTokenEntity accessToken, Set<Role> userRoles);

}

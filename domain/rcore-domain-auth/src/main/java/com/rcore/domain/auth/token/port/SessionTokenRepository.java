package com.rcore.domain.auth.token.port;

import java.util.Optional;

public interface SessionTokenRepository {

    /**
     * Получение токена авторизации текущей сессии
     * @return
     */
    Optional<String> getSessionAccessToken();

    /**
     * Получение токена обноления текущей сессии
     * @return
     */
    Optional<String> getSessionRefreshToken();
}

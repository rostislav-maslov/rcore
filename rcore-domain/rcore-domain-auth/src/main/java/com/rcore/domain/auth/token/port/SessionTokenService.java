package com.rcore.domain.auth.token.port;

import java.util.Optional;

public interface SessionTokenService {

    /**
     * Получение токена авторизации текущей сессии
     * @return
     */
    String getSessionAccessToken();
}

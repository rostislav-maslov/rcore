package com.rcore.domain.auth.token.port;

import java.util.Optional;

public interface SessionTokenRepository {

    /**
     * Получение токена текущей сессии, например из хеадера запроса
     * @return
     */
    Optional<String> getSessionToken();
}

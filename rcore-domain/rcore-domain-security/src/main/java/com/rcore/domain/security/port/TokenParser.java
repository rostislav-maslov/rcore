package com.rcore.domain.security.port;

public interface TokenParser<Model> {

    /**
     * Расшифрование строкового токена в модель с валидацией входной строки
     * @param token
     * @return
     */
    Model parseWithValidating(String token);

}

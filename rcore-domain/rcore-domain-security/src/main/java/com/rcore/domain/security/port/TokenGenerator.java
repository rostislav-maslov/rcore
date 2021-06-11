package com.rcore.domain.security.port;

public interface TokenGenerator<Model> {

    /**
     * Создание строкового токена на основе модели
     * @param token
     * @return
     */
    String generate(Model token);

}

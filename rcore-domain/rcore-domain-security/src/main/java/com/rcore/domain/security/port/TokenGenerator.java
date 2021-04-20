package com.rcore.domain.security.port;

import com.rcore.domain.security.exceptions.ConvertingTokenException;
import com.rcore.domain.security.exceptions.InvalidTokenException;
import com.rcore.domain.security.exceptions.ParsingTokenException;
import com.rcore.domain.security.exceptions.TokenIsExpiredException;

public interface TokenGenerator<Model> {

    /**
     * Создание строкового токена на основе модели
     * @param token
     * @return
     */
    String generate(Model token) throws ConvertingTokenException;

}

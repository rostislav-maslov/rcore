package com.rcore.domain.security.port;

import com.rcore.domain.security.exceptions.ConvertingTokenException;
import com.rcore.domain.security.exceptions.InvalidTokenException;
import com.rcore.domain.security.exceptions.ParsingTokenException;

public interface TokenConverter<Model> {

    /**
     * Создание строкового токена на основе модели
     * @param token
     * @return
     */
    String convert(Model token) throws ConvertingTokenException;

    /**
     * Парсинг строкового токена в модель
     * @param token
     * @return
     */
    Model parse(String token) throws ParsingTokenException;

    /**
     * Праверка строкового токена на валидность
     * @param token
     */
    void validateStringToken(String token) throws InvalidTokenException;

}

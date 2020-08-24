package com.rcore.restapi.exceptions;

import com.rcore.restapi.exceptions.BadRequestApiException;

public class InvalidPasswordApiException extends BadRequestApiException {

    public InvalidPasswordApiException() {
        super("Ошибка авторизации", "Пожалуйста, укажите корректный пароль", "AUTH", "INVALID_PASSWORD");
    }
}

package com.rcore.restapi.exceptions;

import com.rcore.restapi.exceptions.BadRequestApiException;

public class InvalidEmailOrPasswordApiException extends BadRequestApiException {

    public InvalidEmailOrPasswordApiException() {
        super("Ошибка авторизации", "Пожалуйста, укажите корректные email и пароль ", "AUTH", "INVALID_DATA");
    }
}

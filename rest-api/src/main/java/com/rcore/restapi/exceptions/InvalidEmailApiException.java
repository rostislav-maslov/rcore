package com.rcore.restapi.exceptions;

import com.rcore.restapi.exceptions.BadRequestApiException;

public class InvalidEmailApiException extends BadRequestApiException {

    public InvalidEmailApiException() {
        super("Ошибка авторизации", "Пожалуйста, укажите корректный email-адрес", "AUTH", "INVALID_EMAIL");
    }
}

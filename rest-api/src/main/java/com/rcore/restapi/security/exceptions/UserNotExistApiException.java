package com.rcore.restapi.security.exceptions;

public class UserNotExistApiException extends ApiAuthenticationException {

    public UserNotExistApiException() {
        super("Ошибка доступа", "Переданы некорректные данные. Пожалуйста, выполните вход повторно", "USER", "NOT_EXIST");
    }
}

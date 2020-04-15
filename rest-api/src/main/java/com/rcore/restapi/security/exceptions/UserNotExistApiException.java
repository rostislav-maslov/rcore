package com.rcore.restapi.security.exceptions;

public class UserNotExistApiException extends ApiAuthenticationException {

    public UserNotExistApiException() {
        super("Пользователя не существует", "USER", "NOT_EXIST");
    }
}

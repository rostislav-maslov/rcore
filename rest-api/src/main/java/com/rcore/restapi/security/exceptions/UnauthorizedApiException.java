package com.rcore.restapi.security.exceptions;

public class UnauthorizedApiException extends ApiAuthenticationException {

    public UnauthorizedApiException() {
        super("Доступ запрещён", "Не предоставлены данные для аутентификации", "AUTH", "UNAUTHORIZED");
    }
}

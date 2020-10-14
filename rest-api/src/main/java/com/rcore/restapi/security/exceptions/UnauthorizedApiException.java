package com.rcore.restapi.security.exceptions;

public class UnauthorizedApiException extends ApiAuthenticationException {

    public UnauthorizedApiException() {
        super("Ошибка доступа", "Не предоставлены данные для аутентификации", "AUTH", "UNAUTHORIZED");
    }
}

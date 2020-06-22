package com.rcore.restapi.security.exceptions;

public class AuthServiceNotAvailableException extends ApiAuthenticationException {

    public AuthServiceNotAvailableException() {
        super("Ошибка доступа", "Сервис авторизации временно недоступен.", "AUTH", "AUTH_SERVICE_NOT_AVAILABLE");
    }
}

package com.rcore.restapi.security.exceptions;

public class TokenExpiredApiException extends ApiAuthenticationException {

    public TokenExpiredApiException() {
        super("Ошибка доступа", "Передан истёкший токен авторизации", "AUTH", "EXPIRED_TOKEN");
    }
}

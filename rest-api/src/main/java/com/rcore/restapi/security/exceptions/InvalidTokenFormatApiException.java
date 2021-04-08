package com.rcore.restapi.security.exceptions;

public class InvalidTokenFormatApiException extends ApiAuthenticationException {
    public InvalidTokenFormatApiException() {
        super("Доступ запрещён", "Некорректный формат данных для аутентификации", "AUTH", "INVALID_TOKEN_FORMAT");
    }
}

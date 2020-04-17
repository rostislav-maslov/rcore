package com.rcore.restapi.security.exceptions;

public class InvalidTokenFormatApiException extends ApiAuthenticationException {
    public InvalidTokenFormatApiException() {
        super("Неверный формат токена авторизации", "AUTH", "INVALID_TOKEN_FORMAT");
    }
}

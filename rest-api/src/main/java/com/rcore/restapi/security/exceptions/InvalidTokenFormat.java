package com.rcore.restapi.security.exceptions;

public class InvalidTokenFormat extends ApiAuthenticationException {
    public InvalidTokenFormat() {
        super("Неверный формат токена авторизации", "AUTH", "INVALID_TOKEN_FORMAT");
    }
}

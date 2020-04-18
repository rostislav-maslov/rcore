package com.rcore.restapi.security.exceptions;

public class TokenGenerateApiException extends ApiAuthenticationException {

    public TokenGenerateApiException() {
        super("Ошибка сервера", "SERVER", "GENERATE_TOKEN_EXCEPTION");
    }
}

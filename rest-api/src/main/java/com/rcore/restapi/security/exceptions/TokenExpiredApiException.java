package com.rcore.restapi.security.exceptions;

public class TokenExpiredApiException extends ApiAuthenticationException {

    public TokenExpiredApiException() {
        super("Токен просрочен", "AUTH", "EXPIRED_TOKEN");
    }
}

package com.rcore.restapi.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserBlockedApiException extends ApiAuthenticationException {

    public UserBlockedApiException() {
        super("Доступ запрещен", "Ваш аккаунт временно заблокирован по решению администрации сервиса", "AUTH", "ACCOUNT_IS_BLOCKED");
    }
}

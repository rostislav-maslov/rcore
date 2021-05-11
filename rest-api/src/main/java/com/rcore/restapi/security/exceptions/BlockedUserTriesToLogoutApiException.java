package com.rcore.restapi.security.exceptions;

public class BlockedUserTriesToLogoutApiException extends ApiAuthenticationException {

    public BlockedUserTriesToLogoutApiException() {
        super("Доступ запрещён", "Ваш аккаунт временно заблокирован по решению администрации сервиса", "USER", "ACCOUNT_IS_BLOCKED");
    }
}

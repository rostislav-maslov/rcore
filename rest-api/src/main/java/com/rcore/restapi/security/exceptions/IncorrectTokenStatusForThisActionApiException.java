package com.rcore.restapi.security.exceptions;

public class IncorrectTokenStatusForThisActionApiException extends ApiAuthenticationException {

    public IncorrectTokenStatusForThisActionApiException() {
        super("Доступ запрещён", "Переданный токен имеет статус запрещающий проводить данную операцию", "AUTH", "INCORRECT_TOKEN_STATUS");
    }
}

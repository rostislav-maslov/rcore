package com.rcore.restapi.exceptions;

public class InvalidOldPasswordApiException extends BaseApiException {

    public InvalidOldPasswordApiException() {
        super("Невозможно изменить пароль", "Переданный пароль не соответствует актуальному", "USER", "INVALID_OLD_PASSWORD");
    }
}

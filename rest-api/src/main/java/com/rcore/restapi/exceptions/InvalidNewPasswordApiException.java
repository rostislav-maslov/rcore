package com.rcore.restapi.exceptions;

public class InvalidNewPasswordApiException extends BaseApiException {

    public InvalidNewPasswordApiException() {
        super("Невозможно изменить пароль", "Новый пароль не может быть пустым ", "USER", "EMPTY_NEW_PASSWORD");
    }
}

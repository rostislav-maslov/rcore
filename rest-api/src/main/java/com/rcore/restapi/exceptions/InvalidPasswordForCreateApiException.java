package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidPasswordForCreateApiException extends BadRequestApiException {

    public InvalidPasswordForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Необходимо указать пароль пользователя")
                        .build())
                .domain("USER")
                .details("INVALID_PASSWORD")
                .build()));
    }
}

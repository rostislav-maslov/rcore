package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidFirstNameForCreateApiException extends BadRequestApiException {

    public InvalidFirstNameForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Необходимо указать имя пользователя")
                        .build())
                .domain("USER")
                .details("INVALID_FIRST_NAME")
                .build()));
    }
}

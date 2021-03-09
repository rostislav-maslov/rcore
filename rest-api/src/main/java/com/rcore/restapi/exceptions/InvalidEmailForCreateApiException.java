package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidEmailForCreateApiException extends BadRequestApiException {

    public InvalidEmailForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Необходимо указать email пользователя")
                        .build())
                .domain("USER")
                .details("INVALID_EMAIL")
                .build()));
    }
}

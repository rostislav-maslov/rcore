package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidLastNameForCreateApiException extends BadRequestApiException {

    public InvalidLastNameForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Необходимо указать фамилию пользователя")
                        .build())
                .domain("USER")
                .details("INVALID_LAST_NAME")
                .build()));
    }
}

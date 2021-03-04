package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidLastNameApiException extends BadRequestApiException {

    public InvalidLastNameApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Необходимо указать фамилию пользователя")
                        .build())
                .domain("USER")
                .details("INVALID_LAST_NAME")
                .build()));
    }

}
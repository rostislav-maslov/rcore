package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidFirstNameForUpdateApiException extends BadRequestApiException {

    public InvalidFirstNameForUpdateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Необходимо указать имя пользователя")
                        .build())
                .domain("USER")
                .details("INVALID_FIRST_NAME")
                .build()));
    }

}

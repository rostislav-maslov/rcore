package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidFirstNameApiException extends BadRequestApiException {

    public InvalidFirstNameApiException() {
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

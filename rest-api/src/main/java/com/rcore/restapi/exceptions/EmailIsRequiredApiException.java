package com.rcore.restapi.exceptions;

import java.util.Arrays;
import java.util.List;

public class EmailIsRequiredApiException extends BadRequestApiException {

    public EmailIsRequiredApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Необходимо указать email пользователя")
                        .build())
                .domain("USER")
                .details("INVALID_EMAIL")
                .build()));
    }
}

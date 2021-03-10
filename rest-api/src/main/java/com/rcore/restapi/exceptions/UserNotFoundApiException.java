package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class UserNotFoundApiException extends NotFoundApiException {

    public UserNotFoundApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Запись не найдена")
                        .build())
                .domain("USER")
                .details("INVALID_ID")
                .build()));
    }
}

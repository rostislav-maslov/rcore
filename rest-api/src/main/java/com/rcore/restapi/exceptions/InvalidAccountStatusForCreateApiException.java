package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidAccountStatusForCreateApiException extends BadRequestApiException {

    public InvalidAccountStatusForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Необходимо указать тип активности")
                        .build())
                .domain("USER")
                .details("INVALID_ACCOUNT_STATUS")
                .build()));
    }
}

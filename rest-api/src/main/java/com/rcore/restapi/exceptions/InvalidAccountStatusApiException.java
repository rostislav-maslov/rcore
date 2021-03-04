package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidAccountStatusApiException extends BadRequestApiException {

    public InvalidAccountStatusApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Необходимо указать тип активности")
                        .build())
                .domain("USER")
                .details("INVALID_ACCOUNT_STATUS")
                .build()));
    }

}

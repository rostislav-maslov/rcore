package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidAccountStatusForUpdateApiException extends BadRequestApiException {

    public InvalidAccountStatusForUpdateApiException() {
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

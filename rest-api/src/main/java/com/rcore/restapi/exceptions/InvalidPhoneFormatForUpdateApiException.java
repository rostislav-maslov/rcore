package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidPhoneFormatForUpdateApiException extends BadRequestApiException {

    public InvalidPhoneFormatForUpdateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Переданный номер не соответствует формату, применяемому в выбранной стране")
                        .build())
                .domain("USER")
                .details("INVALID_PHONE_NUMBER")
                .build()));
    }
}

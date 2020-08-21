package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class InvalidPhoneNumberFormatApiException extends BadRequestApiException {

    public InvalidPhoneNumberFormatApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Ошибка сервера")
                        .message("Некорректный формат номера телефона")
                        .build())
                .domain("SERVER")
                .details("INVALID_PHONE_NUMBER_FORMAT")
                .build()));
    }
}

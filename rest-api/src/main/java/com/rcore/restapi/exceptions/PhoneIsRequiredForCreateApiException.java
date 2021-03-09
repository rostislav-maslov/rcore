package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class PhoneIsRequiredForCreateApiException extends BadRequestApiException {

    public PhoneIsRequiredForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Необходимо указать номер телефона")
                        .build())
                .domain("USER")
                .details("INVALID_PHONE")
                .build()));
    }
}

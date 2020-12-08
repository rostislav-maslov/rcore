package com.rcore.restapi.exceptions;

import java.util.Arrays;
import java.util.List;

public class PhoneIsRequiredApiException extends BadRequestApiException {

    public PhoneIsRequiredApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Необходимо указать номер телефона")
                        .build())
                .domain("USER")
                .details("INVALID_PHONE")
                .build()));
    }
}

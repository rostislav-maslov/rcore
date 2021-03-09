package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class RoleNotFoundForCreateApiException extends BadRequestApiException {

    public RoleNotFoundForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Передан некорректный идентификатор роли")
                        .build())
                .domain("USER")
                .details("INVALID_ROLE_ID")
                .build()));
    }
}

package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class RoleNotFoundForUpdateApiException extends NotFoundApiException {

    public RoleNotFoundForUpdateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Передан некорректный идентификатор роли")
                        .build())
                .domain("USER")
                .details("INVALID_ROLE")
                .build()));
    }
}

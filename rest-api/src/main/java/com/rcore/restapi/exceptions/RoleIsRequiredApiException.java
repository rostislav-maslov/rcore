package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class RoleIsRequiredApiException extends BadRequestApiException {

    public RoleIsRequiredApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Необходимо выбрать хотя бы одну роль для пользователя")
                        .build())
                .domain("USER")
                .details("ROLES_NOT_SELECTED")
                .build()));
    }
}

package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class RoleIsRequiredForCreateApiException extends BadRequestApiException {

    public RoleIsRequiredForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Необходимо выбрать хотя бы одну роль для пользователя")
                        .build())
                .domain("USER")
                .details("ROLES_NOT_SELECTED")
                .build()));
    }
}

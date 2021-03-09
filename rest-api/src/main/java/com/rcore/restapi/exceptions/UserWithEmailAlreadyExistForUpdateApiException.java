package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class UserWithEmailAlreadyExistForUpdateApiException extends BadRequestApiException {

    public UserWithEmailAlreadyExistForUpdateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Пользователь с данным email уже существует")
                        .build())
                .domain("USER")
                .details("EMAIL_ALREADY_EXIST")
                .build()));
    }
}

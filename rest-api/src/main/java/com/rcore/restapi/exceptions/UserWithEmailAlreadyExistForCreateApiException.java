package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class UserWithEmailAlreadyExistForCreateApiException extends BadRequestApiException {

    public UserWithEmailAlreadyExistForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Пользователь с данным email уже существует")
                        .build())
                .domain("USER")
                .details("EMAIL_ALREADY_EXIST")
                .build()));
    }
}

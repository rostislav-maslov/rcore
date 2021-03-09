package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class UserWithPhoneAlreadyExistForCreateApiException extends BadRequestApiException {

    public UserWithPhoneAlreadyExistForCreateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось сохранить данные")
                        .message("Пользователь с данным номером уже существует")
                        .build())
                .domain("USER")
                .details("PHONENUMBER_ALREADY_EXIST")
                .build()));
    }
}

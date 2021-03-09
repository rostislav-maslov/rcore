package com.rcore.restapi.exceptions;

import java.util.Arrays;

public class UserWithPhoneAlreadyExistForUpdateApiException extends BadRequestApiException {

    public UserWithPhoneAlreadyExistForUpdateApiException() {
        super(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Не удалось изменить параметры")
                        .message("Пользователь с данным номером уже существует")
                        .build())
                .domain("USER")
                .details("PHONENUMBER_ALREADY_EXIST")
                .build()));
    }
}

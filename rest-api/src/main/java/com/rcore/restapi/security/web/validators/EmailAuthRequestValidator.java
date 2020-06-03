package com.rcore.restapi.security.web.validators;

import com.rcore.restapi.exceptions.BadRequestApiException;
import com.rcore.restapi.security.web.api.UserCredentialsDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmailAuthRequestValidator {

    public final BadRequestApiException invalidEmail = new BadRequestApiException("Ошибка авторизации", "Пожалуйста, укажите корректный email-адрес", "AUTH", "INVALID_EMAIL");
    public final BadRequestApiException invalidPassword = new BadRequestApiException("Ошибка авторизации", "Пожалуйста, укажите корректный пароль", "AUTH", "INVALID_PASSWORD");
    public final BadRequestApiException invalidEmailOrPassword = new BadRequestApiException("Ошибка авторизации", "Пожалуйста, укажите корректные email и пароль ", "AUTH", "INVALID_DATA");

    public void validate(UserCredentialsDTO request) throws BadRequestApiException {
        if (!StringUtils.hasText(request.getEmail()) && !StringUtils.hasText(request.getPassword()))
            throw invalidEmailOrPassword;

        if (!StringUtils.hasText(request.getEmail()))
            throw invalidEmail;

        if (!StringUtils.hasText(request.getPassword()))
            throw invalidPassword;
    }

}

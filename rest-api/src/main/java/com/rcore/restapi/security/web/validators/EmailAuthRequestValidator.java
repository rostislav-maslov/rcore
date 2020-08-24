package com.rcore.restapi.security.web.validators;

import com.rcore.restapi.exceptions.BadRequestApiException;
import com.rcore.restapi.exceptions.InvalidEmailApiException;
import com.rcore.restapi.exceptions.InvalidEmailOrPasswordApiException;
import com.rcore.restapi.exceptions.InvalidPasswordApiException;
import com.rcore.restapi.security.web.api.UserCredentialsDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmailAuthRequestValidator {

    public final InvalidEmailApiException invalidEmail = new InvalidEmailApiException();
    public final InvalidPasswordApiException invalidPassword = new InvalidPasswordApiException();
    public final InvalidEmailOrPasswordApiException invalidEmailOrPassword = new InvalidEmailOrPasswordApiException();

    public void validate(UserCredentialsDTO request) throws BadRequestApiException {
        if (!StringUtils.hasText(request.getEmail()) && !StringUtils.hasText(request.getPassword()))
            throw invalidEmailOrPassword;

        if (!StringUtils.hasText(request.getEmail()))
            throw invalidEmail;

        if (!StringUtils.hasText(request.getPassword()))
            throw invalidPassword;
    }

}

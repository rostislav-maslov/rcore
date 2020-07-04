package com.rcore.restapi.security.exceptions;

import com.rcore.restapi.exceptions.ExceptionDTO;

import java.util.List;

public class AuthForbiddenApiException extends ApiAuthenticationException  {

    public AuthForbiddenApiException(List<ExceptionDTO> errors) {
        super(errors);
    }
}

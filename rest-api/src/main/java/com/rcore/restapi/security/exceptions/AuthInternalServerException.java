package com.rcore.restapi.security.exceptions;

import com.rcore.restapi.exceptions.ExceptionDTO;

import java.util.List;

public class AuthInternalServerException extends ApiAuthenticationException {

    public AuthInternalServerException(List<ExceptionDTO> errors) {
        super(errors);
    }
}

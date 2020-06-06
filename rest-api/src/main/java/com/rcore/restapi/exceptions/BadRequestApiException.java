package com.rcore.restapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class BadRequestApiException extends BaseApiException {

    public BadRequestApiException(List<ExceptionDTO> errors) {
        super(errors);
    }

    public BadRequestApiException(ExceptionDTO error) {
        super(error);
    }

    public BadRequestApiException(String title, String message, String domain, String details) {
        super(title, message, domain, details);
    }

    public BadRequestApiException(String message, String domain, String details) {
        super(message, domain, details);
    }
}

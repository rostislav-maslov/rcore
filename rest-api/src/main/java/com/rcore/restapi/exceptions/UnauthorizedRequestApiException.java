package com.rcore.restapi.exceptions;

import java.util.List;

public class UnauthorizedRequestApiException extends BaseApiException {

    @Override
    public List<ExceptionDTO> getErrors() {
        return super.getErrors();
    }

    public UnauthorizedRequestApiException(List<ExceptionDTO> errors) {
        super(errors);
    }

    public UnauthorizedRequestApiException(ExceptionDTO error) {
        super(error);
    }

    public UnauthorizedRequestApiException(String title, String message, String domain, String details) {
        super(title, message, domain, details);
    }

    public UnauthorizedRequestApiException(String message, String domain, String details) {
        super(message, domain, details);
    }
}

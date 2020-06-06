package com.rcore.restapi.exceptions;

import java.util.List;

public class NotFoundApiException extends BaseApiException {

    public NotFoundApiException(List<ExceptionDTO> errors) {
        super(errors);
    }

    public NotFoundApiException(ExceptionDTO error) {
        super(error);
    }

    public NotFoundApiException(String title, String message, String domain, String details) {
        super(title, message, domain, details);
    }

    public NotFoundApiException(String message, String domain, String details) {
        super(message, domain, details);
    }
}

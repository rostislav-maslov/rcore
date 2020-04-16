package com.rcore.restapi.exceptions;

public class InternalServerException extends BaseApiException {

    public InternalServerException() {
    }

    public InternalServerException(ExceptionDTO errors) {
        super(errors);
    }

    public InternalServerException(String title, String message, String domain, String details) {
        super(title, message, domain, details);
    }

    public InternalServerException(String message, String domain, String details) {
        super(message, domain, details);
    }
}

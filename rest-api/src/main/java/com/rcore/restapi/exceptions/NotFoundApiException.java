package com.rcore.restapi.exceptions;

public class NotFoundApiException extends BaseApiException {

    public NotFoundApiException(ExceptionDTO errors) {
        super(errors);
    }


    public NotFoundApiException(String title, String message, String domain, String details) {
        super(title, message, domain, details);
    }

    public NotFoundApiException(String message, String domain, String details) {
        super(message, domain, details);
    }
}

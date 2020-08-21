package com.rcore.restapi.exceptions;

import java.util.List;

public class ServiceUnavailableApiException extends BaseApiException {

    public ServiceUnavailableApiException() {
    }

    public ServiceUnavailableApiException(List<ExceptionDTO> errors) {
        super(errors);
    }

    public ServiceUnavailableApiException(ExceptionDTO error) {
        super(error);
    }

    public ServiceUnavailableApiException(String title, String message, String domain, String details) {
        super(title, message, domain, details);
    }

    public ServiceUnavailableApiException(String message, String domain, String details) {
        super(message, domain, details);
    }
}

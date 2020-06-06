package com.rcore.restapi.exceptions;

import java.util.List;

public class TooManyRequestApiException extends BaseApiException {

    public TooManyRequestApiException(List<ExceptionDTO> error) {
        super(error);
    }

    public TooManyRequestApiException(ExceptionDTO error) {
        super(error);
    }

    public TooManyRequestApiException(String title, String message, String domain, String details) {
        super(title, message, domain, details);
    }

    public TooManyRequestApiException(String message, String domain, String details) {
        super(message, domain, details);
    }
}

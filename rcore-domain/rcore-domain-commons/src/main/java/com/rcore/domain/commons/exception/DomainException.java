package com.rcore.domain.commons.exception;

import lombok.Getter;

public class DomainException extends RuntimeException {

    @Getter
    private String domain;

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, String domain) {
        super(message);
        this.domain = domain;
    }

    public DomainException(String message, Throwable cause, String domain) {
        super(message, cause);
        this.domain = domain;
    }

    public DomainException(Throwable cause, String domain) {
        super(cause);
        this.domain = domain;
    }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String domain) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.domain = domain;
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

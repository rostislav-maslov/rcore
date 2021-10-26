package com.rcore.domain.commons.exception;

import lombok.*;

import javax.validation.Payload;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
public abstract class DomainException extends RuntimeException {

    @Setter
    private List<Error> errors = new ArrayList<>();
    private UUID traceId = UUID.randomUUID();

    @ToString
    @NoArgsConstructor
    @Getter
    public static class Error {
        protected String domain;
        protected String reason;
        protected String details;
        protected String invalidFieldName;
        protected String invalidValue;

        public Error(String domain, String reason, String details) {
            this.domain = domain;
            this.reason = reason;
            this.details = details;
        }

        public Error(String domain, String reason, String details, String invalidFieldName) {
            this.domain = domain;
            this.reason = reason;
            this.details = details;
            this.invalidFieldName = invalidFieldName;
        }

        public Error(String domain, String reason, String details, String invalidFieldName, String invalidValue) {
            this.domain = domain;
            this.reason = reason;
            this.details = details;
            this.invalidFieldName = invalidFieldName;
            this.invalidValue = invalidValue;
        }
    }

    public DomainException() {
    }


    public DomainException(String message) {
        super(message);
    }

    public DomainException(String domain, String reason, String details) {
        super(details);
        this.errors = Collections.singletonList(new Error(domain, reason, details));
    }

    public DomainException(String domain, String reason, String details, String invalidFieldName) {
        super(details);
        this.errors = Collections.singletonList(new Error(domain, reason, details, invalidFieldName));
    }

    public DomainException(String domain, String reason, String details, String invalidFieldName, String invalidValue) {
        super(details);
        this.errors = Collections.singletonList(new Error(domain, reason, details, invalidFieldName, invalidValue));
    }

    public DomainException(Error error) {
        this.errors = Collections.singletonList(error);
    }

    public DomainException(List<Error> errors) {
        this.errors = errors;
    }

    public DomainException(String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public DomainException(String message, Throwable cause, List<Error> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public DomainException(Throwable cause, List<Error> errors) {
        super(cause);
        this.errors = errors;
    }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
    }
}

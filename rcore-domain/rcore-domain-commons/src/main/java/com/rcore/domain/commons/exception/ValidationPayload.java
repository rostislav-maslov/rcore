package com.rcore.domain.commons.exception;

import lombok.Getter;

import javax.validation.Payload;

@Getter
public class ValidationPayload implements Payload {
    private final String domain;
    private final String reason;

    public ValidationPayload(String domain) {
        this.domain = domain;
        this.reason = null;
    }

    public ValidationPayload(String domain, String reason) {
        this.domain = domain;
        this.reason = reason;
    }
}

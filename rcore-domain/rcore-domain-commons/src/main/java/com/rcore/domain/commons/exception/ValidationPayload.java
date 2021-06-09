package com.rcore.domain.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Payload;

@AllArgsConstructor
@Getter
public class ValidationPayload implements Payload {
    private final String domain;
    private final String reason;
}

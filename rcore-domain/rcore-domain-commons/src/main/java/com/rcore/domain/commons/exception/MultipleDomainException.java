package com.rcore.domain.commons.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Исключение, содержащее множество исключений
 */
public class MultipleDomainException extends DomainException {

    @Getter
    private final List<DomainException> exceptions = new ArrayList<>();

    public MultipleDomainException(String message, String domain) {
        super(message, domain);
    }

    public void addException(DomainException e) {
        this.exceptions.add(e);
    }

    public void addAllExceptions(List<DomainException> exceptions) {
        this.exceptions.addAll(exceptions);
    }
}

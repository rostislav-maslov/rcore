package com.rcore.domain.commons.exception;

import com.rcore.domain.commons.exception.factory.ValidationErrorFactory;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ValidatingDomainException extends BadRequestDomainException {

    public <T> ValidatingDomainException(Set<ConstraintViolation<T>> invalidFields) {
        setErrors(invalidFields
                .stream()
                .map(ValidationErrorFactory::buildError)
                .collect(Collectors.toList()));
    }
}

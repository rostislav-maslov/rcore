package com.rcore.domain.commons.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ValidatingDomainException extends BadRequestDomainException {

    public <T> ValidatingDomainException(Set<ConstraintViolation<T>> invalidFields) {
        setErrors(invalidFields
                .stream()
                .map(this::buildError)
                .collect(Collectors.toList()));
    }

    public <T> Error buildError(ConstraintViolation<T> c) {

        AtomicReference<Error> error = new AtomicReference<>(new Error(GlobalDomain.SERVER, GlobalReason.UNKNOWN_REASON, c.getMessage()));

        c.getConstraintDescriptor().getPayload()
                .forEach(p -> {
                    if (ValidationPayload.class.isAssignableFrom(p)) {
                        try {
                            var payload = (ValidationPayload) p.getDeclaredConstructor().newInstance();
                            error.set(new Error(payload.getDomain(), payload.getReason(), c.getMessage()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        return error.get();
    }
}

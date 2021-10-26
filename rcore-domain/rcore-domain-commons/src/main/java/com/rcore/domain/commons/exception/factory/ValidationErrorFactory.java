package com.rcore.domain.commons.exception.factory;

import com.rcore.domain.commons.exception.*;
import com.rcore.domain.commons.validators.ValidationDomain;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class ValidationErrorFactory {

    private final static HashMap<String, Class<?>> map = new HashMap<>() {{
        put("javax.validation.constraints.AssertFalse", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.AssertTrue", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.DecimalMax", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.DecimalMin", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Digits", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Email", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Future", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.FutureOrPresent", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Max", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Min", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Negative", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.NegativeOrZero", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.NotBlank", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Null", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Past", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.PastOrPresent", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Pattern", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Positive", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.PositiveOrZero", DefaultIncorrectValueException.Error.class);
        put("javax.validation.constraints.Size", DefaultIncorrectValueException.Error.class);

        put("javax.validation.constraints.NotNull", DefaultValueIsRequiredException.Error.class);
        put("javax.validation.constraints.NotEmpty", DefaultValueIsRequiredException.Error.class);
    }};

    public static <T> DomainException.Error buildError(ConstraintViolation<T> c) {
        AtomicReference<DomainException.Error> error = new AtomicReference<>(new DomainException.Error(GlobalDomain.SERVER, GlobalReason.UNKNOWN_REASON, c.getMessage()));
        var annotationName = c.getConstraintDescriptor().getAnnotation().annotationType().getName();

        ValidationDomain validationDomain = c.getRootBeanClass().getAnnotation(ValidationDomain.class);
        Optional<ValidationPayload> validationPayload = getValidationPayload(c.getConstraintDescriptor().getPayload());

        if (validationPayload.isPresent() && validationPayload.get().getReason() != null && validationPayload.get().getDomain() != null)
            return new DomainException.Error(validationPayload.get().getDomain(), validationPayload.get().getReason(), c.getMessage());

        var errorClass = Objects.requireNonNullElse(map.get(annotationName), DomainException.Error.class);
        var domain = validationPayload.map(ValidationPayload::getDomain).orElseGet(() -> {
            if (validationDomain != null)
                return validationDomain.domainName();
            return "";
        });
        var propertyPath = c.getPropertyPath().toString();

        if (errorClass.getName().equals(DefaultIncorrectValueException.Error.class.getName()))
            error.set(new DefaultIncorrectValueException.Error(domain, propertyPath));
        else if (errorClass.getName().equals(DefaultValueIsRequiredException.Error.class.getName()))
            error.set(new DefaultValueIsRequiredException.Error(domain, propertyPath));
        else if (errorClass.getName().equals(DefaultValueIsNotUniqueException.Error.class.getName()))
            error.set(new DefaultValueIsNotUniqueException.Error(domain, propertyPath));

        return error.get();
    }

    private static Optional<ValidationPayload> getValidationPayload(Set<Class<? extends Payload>> payloads) {
        AtomicReference<ValidationPayload> result = new AtomicReference<>();
        payloads.forEach(p -> {
            if (ValidationPayload.class.isAssignableFrom(p)) {
                try {
                    var payload = (ValidationPayload) p.getDeclaredConstructor().newInstance();
                    result.set(payload);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return Optional.ofNullable(result.get());
    }
}

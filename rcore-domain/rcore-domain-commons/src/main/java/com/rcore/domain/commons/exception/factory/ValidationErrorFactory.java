package com.rcore.domain.commons.exception.factory;

import com.rcore.domain.commons.exception.*;
import com.rcore.domain.commons.validators.ValidationDomain;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ValidationErrorFactory {

    private final static Map<String, String> supportedAnnotations = new HashMap<>() {{
        put("javax.validation.constraints.AssertFalse", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.AssertTrue", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.DecimalMax", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.DecimalMin", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Digits", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Email", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Future", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.FutureOrPresent", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Max", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Min", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Negative", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.NegativeOrZero", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.NotBlank", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Null", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Past", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.PastOrPresent", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Pattern", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Positive", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.PositiveOrZero", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.Size", GlobalReason.IS_INCORRECT_POSTFIX);

        put("javax.validation.constraints.NotNull", GlobalReason.IS_INCORRECT_POSTFIX);
        put("javax.validation.constraints.NotEmpty", GlobalReason.IS_INCORRECT_POSTFIX);
    }};

    public static void addSupportedAnnotation(Class<? extends Annotation> annotation, String reason) {
        supportedAnnotations.put(annotation.getName(), reason);
    }

    public static void addSupportedAnnotations(Map<Class<? extends Annotation>, String> map) {
        map.forEach((k, v) -> supportedAnnotations.put(k.getName(), v));
    }

    public static <T> DomainException.Error buildError(ConstraintViolation<T> c) {
        AtomicReference<DomainException.Error> error = new AtomicReference<>(new DomainException.Error(GlobalDomain.SERVER, GlobalReason.UNKNOWN_REASON, c.getMessage()));
        var annotationName = c.getConstraintDescriptor().getAnnotation().annotationType().getName();

        ValidationDomain validationDomain = c.getRootBeanClass().getAnnotation(ValidationDomain.class);
        Optional<ValidationPayload> validationPayload = getValidationPayload(c.getConstraintDescriptor().getPayload());

        if (validationPayload.isPresent() && validationPayload.get().getReason() != null && validationPayload.get().getDomain() != null)
            return new DomainException.Error(validationPayload.get().getDomain(), validationPayload.get().getReason(), c.getMessage());

        var reason = Objects.requireNonNullElse(supportedAnnotations.get(annotationName), "");
        var domain = validationPayload.map(ValidationPayload::getDomain).orElseGet(() -> {
            if (validationDomain != null)
                return validationDomain.domainName();
            return "";
        });
        var propertyPath = c.getPropertyPath().toString();

        if (reason.equals(GlobalReason.IS_INCORRECT_POSTFIX))
            error.set(new DefaultIncorrectValueException.Error(domain, propertyPath));
        else if (reason.equals(GlobalReason.IS_REQUIRED_POSTFIX))
            error.set(new DefaultValueIsRequiredException.Error(domain, propertyPath));
        else
            error.set(new DomainException.Error(domain, reason, c.getMessage()));

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

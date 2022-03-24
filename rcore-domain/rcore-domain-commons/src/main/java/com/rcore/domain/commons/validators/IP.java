package com.rcore.domain.commons.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IPValidator.class)
public @interface IP {
    String message() default "IP is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.rcore.domain.commons.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidatePhoneNumber {
    String message() default "Address is required";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

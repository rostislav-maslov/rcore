package com.rcore.domain.commons.exception;

import com.rcore.domain.commons.exception.factory.ValidationErrorFactory;
import com.rcore.domain.commons.validators.ValidationDomain;
import lombok.Value;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import javax.validation.constraints.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;

class ValidatingDomainExceptionTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testBuilding() {
        ValidationErrorFactory.addSupportedAnnotation(CustomAnnotation.class, "TEST");
        var model = Model.of(null, "", null, null, null);
        var c = validator.validate(model);
        var exception = new ValidatingDomainException(c);
        exception.getErrors().forEach(e -> assertNotNull(e.getReason()));
    }

    public static class AuthPayload extends ValidationPayload {
        public AuthPayload() {
            super("AUTH", "AUTH_TYPE_NOT_SUPPORTED");
        }
    }

    @ValidationDomain(domainName = GlobalDomain.SERVER)
    @Value(staticConstructor = "of")
    public static class Model {
        @NotNull
        String arg1;
        @NotEmpty
        String arg2;
        @NotNull(payload = AuthPayload.class)
        String arg3;
        @CustomAnnotation
        String arg4;
        @CustomAnnotation(payload = AuthPayload.class)
        String arg5;
    }


    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = CustomValidator.class)
    public @interface CustomAnnotation {
        String message() default "Test";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class CustomValidator implements ConstraintValidator<CustomAnnotation, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value != null;
        }
    }
}
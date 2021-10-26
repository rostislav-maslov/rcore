package com.rcore.domain.commons.exception;

import com.rcore.domain.commons.validators.ValidationDomain;
import lombok.Value;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.*;

import static org.junit.jupiter.api.Assertions.*;

class ValidatingDomainExceptionTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testBuilding() {
        var model = Model.of(null, "", null);
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
    }
}
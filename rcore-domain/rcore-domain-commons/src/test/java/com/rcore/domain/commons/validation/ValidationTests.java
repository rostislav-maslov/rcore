package com.rcore.domain.commons.validation;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тестирование Bean-validation
 * Демонстрация базового функционала
 * https://alexkosarev.name/2018/07/30/bean-validation-api/ - неплохая статья
 */
public class ValidationTests {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void ValidateLocation_NullParams() {
        var location = Address.City.Location.of(null, null);
        Set<ConstraintViolation<Address.City.Location>> violations = validator.validate(location);

        assertEquals(violations.size(), 2);

        List<String> invalidProps = violations.stream().map(c -> c.getPropertyPath().toString()).collect(Collectors.toList());
        invalidProps.forEach(p -> {
            assertTrue(Arrays.asList("lat", "lon").contains(p));
        });
    }

    @Test
    void ValidateLocation_LatNull() {
        var location = Address.City.Location.of(null, 10D);
        Set<ConstraintViolation<Address.City.Location>> violations = validator.validate(location);

        assertEquals(violations.size(), 1);

        List<String> invalidProps = violations.stream().map(c -> c.getPropertyPath().toString()).collect(Collectors.toList());
        assertEquals(invalidProps, Collections.singletonList("lat"));
    }

    @Test
    void ValidateAddress_NullCity() {
        var address = Address.builder()
                .id(UUID.randomUUID().toString())
                .street("street")
                .build();

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(violations.size(), 1);
        List<String> invalidProps = violations.stream().map(c -> c.getPropertyPath().toString()).collect(Collectors.toList());
        assertEquals(invalidProps, Collections.singletonList("city"));
    }

    @Test
    void ValidateAddress_InvalidCityName() {
        var address = Address.builder()
                .id(UUID.randomUUID().toString())
                .street("street")
                .city(Address.City.builder()
                        .id(UUID.randomUUID().toString())
                        .location(Address.City.Location.of(10d, 10d))
                        .build())
                .build();

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(violations.size(), 1);
        List<String> invalidProps = violations.stream().map(c -> c.getPropertyPath().toString()).collect(Collectors.toList());
        assertEquals(invalidProps, Collections.singletonList("city.name"));
    }

    @Test
    void ValidateAddress_InvalidCityNameLength() {
        var address = Address.builder()
                .id(UUID.randomUUID().toString())
                .street("street")
                .city(Address.City.builder()
                        .id(UUID.randomUUID().toString())
                        .name("")
                        .location(Address.City.Location.of(10d, 10d))
                        .build())
                .build();

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(violations.size(), 1);
        List<String> invalidProps = violations.stream().map(c -> c.getPropertyPath().toString()).collect(Collectors.toList());
        assertEquals(invalidProps, Collections.singletonList("city.name"));
    }

    @Test
    void ValidateAddress_InvalidCityNameLength_2() {
        var address = Address.builder()
                .id(UUID.randomUUID().toString())
                .street("street")
                .city(Address.City.builder()
                        .id(UUID.randomUUID().toString())
                        .name("12345678901")
                        .location(Address.City.Location.of(10d, 10d))
                        .build())
                .build();

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(violations.size(), 1);
        List<String> invalidProps = violations.stream().map(c -> c.getPropertyPath().toString()).collect(Collectors.toList());
        assertEquals(invalidProps, Collections.singletonList("city.name"));
    }

    @Test
    void ValidateAddress_ValidAddress() {
        var address = Address.builder()
                .id(UUID.randomUUID().toString())
                .street("street")
                .city(Address.City.builder()
                        .id(UUID.randomUUID().toString())
                        .name("MSK")
                        .location(Address.City.Location.of(10d, 10d))
                        .build())
                .build();

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(violations.size(), 0);
    }

    @Test
    void ValidateAddress_InvalidStreet() {
        var address = Address.builder()
                .id(UUID.randomUUID().toString())
                .city(Address.City.builder()
                        .id(UUID.randomUUID().toString())
                        .name("MSK")
                        .location(Address.City.Location.of(10d, 10d))
                        .build())
                .build();

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(violations.size(), 1);
        List<String> messages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertEquals(messages, Collections.singletonList("Invalid street"));
    }

}

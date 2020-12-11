package com.rcore.domain.auth.confirmationCode.port.impl;

import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeGenerator;
import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class ConfirmationCodeGeneratorImpl implements ConfirmationCodeGenerator {

    private final Integer length;

    private final static String numbers = "123456789";
    private final static String letters = "qwertyuiopasdfghjklzxcvbnm";
    private final static String special_symbols = "!@#$%^&*()_+";

    @Override
    public String generate() {
        return Stream.iterate(0, i-> new Random().nextInt(length))
                .limit(length)
                .map(Objects::toString)
                .collect(Collectors.joining());
    }
}

package com.rcore.commons.utils;

import java.util.Optional;

public class DigitsUtils {

    public static Optional<Long> pullOutTheNumbers(String text) {
        return StringUtils.hasText(text)
                ? Optional.of(Long.parseLong(leaveOnlyNumbersOnTheString(text)))
                : Optional.empty();
    }

    public static String leaveOnlyNumbersOnTheString(String text) {
        return text.replaceAll("[^0-9]", "");
    }
}

package com.rcore.domain.commons.validators;

import com.rcore.commons.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator implements ConstraintValidator<Email, String> {

    private final List<Character> localPartAllowedSpecSymbols = Arrays.asList('+', '-', '_', ',', '.', '\'');
    private final List<Character> domainPartAllowedSpecSymbols = Arrays.asList('-', '.');

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(email))
            return true;

        Pattern pattern = Pattern.compile(ValidationPatterns.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            return false;

        return checkSpecialCharacters(email);
    }

    private boolean checkSpecialCharacters(String email) {
        String[] splitEmail = email.split("@");
        return notContainsConsecutiveSpecSymbols(splitEmail[0], localPartAllowedSpecSymbols)
                && notContainsConsecutiveSpecSymbols(splitEmail[1], domainPartAllowedSpecSymbols);
    }

    //Не должно быть несколько подряд идущих спец символов
    private static boolean notContainsConsecutiveSpecSymbols(String s, List<Character> allowedSpecSymbols) {
        var chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (i == chars.length - 1)
                return true;

            if (allowedSpecSymbols.contains(chars[i]) && allowedSpecSymbols.contains(chars[i + 1]))
                return false;
        }
        return true;
    }
}

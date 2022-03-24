package com.rcore.domain.commons.validators;

import com.rcore.commons.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IPValidator implements ConstraintValidator<IP, String> {

    @Override
    public boolean isValid(String ipV4, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(ipV4))
            return true;

        Pattern pattern = Pattern.compile(ValidationPatterns.IPv4_PATTERN);
        Matcher matcher = pattern.matcher(ipV4);
        return matcher.matches();
    }
}

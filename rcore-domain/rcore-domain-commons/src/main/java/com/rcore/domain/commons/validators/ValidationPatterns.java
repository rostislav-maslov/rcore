package com.rcore.domain.commons.validators;

public class ValidationPatterns {
    public static final String EMAIL_PATTERN = "[a-zA-Z\\d]{1}[a-zA-Z-._+'\\d]{0,62}[a-zA-Z\\d]{1}[@]{1}[a-zA-Z\\d]{1}[a-zA-Z-.\\d]{0,62}[a-zA-Z\\d]{1}[.][a-zA-Z]{2,64}";
    public static final String IPv4_PATTERN = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
}

package com.rcore.commons.utils;

import com.rcore.commons.exceptions.InvalidPhoneNumberFormatException;

import java.io.StringWriter;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberUtils {

    public static String format(Long phoneNumber) throws InvalidPhoneNumberFormatException {
        String phone = phoneNumber.toString();
        if (phone.length() != 11 && phone.length() != 12) {
            throw new InvalidPhoneNumberFormatException();
        }

        if (phone.length() == 11) {
            return String.format("+%s %s %s-%s-%s", phone.substring(0, 1), phone.substring(1, 4), phone.substring(4, 7), phone.substring(7, 9), phone.substring(9, 11));
        } else {
            return String.format("+%s %s %s-%s-%s", phone.substring(0, 2), phone.substring(2, 5), phone.substring(5, 8), phone.substring(8, 10), phone.substring(10, 12));
        }
    }

    public static String formatPhone(long lphone) {
        return formatPhone(lphone, 10);
    }

    public static String formatPhone(long lphone, int lengthWithoutCode) {
        String phone = String.valueOf(lphone);
        String regex = "(\\d*)(\\d{" + lengthWithoutCode + "})$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(phone);

        StringBuilder returnValue = new StringBuilder();
        returnValue.append("+");
        if (matcher.find()) {
            returnValue.append(matcher.group(1));
            returnValue.append(formatPhoneWithoutCode(matcher.group(2)));
        }
        return returnValue.toString();
    }

    private static String formatPhoneWithoutCode(String phone) {
        StringBuilder returnValue = new StringBuilder();

        int offset = (phone.length() - 10) * -1;
        returnValue.append(" (").append(phone, 0, 3 - offset).append(") ");
        returnValue.append(phone, 3 - offset, 6 - offset).append("-");
        returnValue.append(phone, 6 - offset, 8 - offset).append("-");
        returnValue.append(phone, 8 - offset, 10 - offset);

        return returnValue.toString();
    }

    public static String format(String phoneNumber) {

        if (phoneNumber.length() == 10) {
            return "+7" + phoneNumber;
        } else if (phoneNumber.length() == 11) {
            if (phoneNumber.startsWith("8")) {
                phoneNumber = "+7" + phoneNumber.substring(1);
            }
            return phoneNumber;
        } else {
            return phoneNumber;
        }
    }

    public static Long parse(String phone) throws InvalidPhoneNumberFormatException {

        StringWriter phoneNumber = new StringWriter();
        phone.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isDigit)
                .forEach(phoneNumber::append);

        phone = phoneNumber.toString();
        if (phone.length() == 10) {
            return Long.parseLong('7' + phone);
        } else if (phone.length() == 11) {
            if (phone.startsWith("8")) {
                phone = '7' + phone.substring(1);
            }
            return Long.parseLong(phone);
        } else if (phone.length() == 12) {
            return Long.parseLong(phone);
        }

        throw new InvalidPhoneNumberFormatException();
    }

    public static Long smartParse(String phone) throws InvalidPhoneNumberFormatException {
        return smartParse(phone, 10);
    }

    public static Long smartParse(String phone, int lengthWithoutCode) throws InvalidPhoneNumberFormatException {
        phone = phone.replaceAll("\\D", "");

        if (phone.length() == 10) return Long.parseLong(phone);
        if (phone.length() > 14 || phone.length() < 10)
            throw new InvalidPhoneNumberFormatException();

        String regex = "(\\d*)(\\d{" + lengthWithoutCode + "})$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(phone);

        long returnValue = 0;
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                String value = matcher.group(i).equals("8") ? "7" : matcher.group(i);
                returnValue += Long.parseLong(value) * Math.max(Math.pow(10, lengthWithoutCode) * (2 - i), 1);
            }
        }
        return returnValue;
    }

}

package com.rcore.domain.user.port.impl;

import com.rcore.domain.user.port.PasswordGenerator;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class PasswordGeneratorImpl implements PasswordGenerator {

    private String salt() {
        String uuid = UUID.randomUUID().toString();
        return encryptThisString(uuid);
    }

    public static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private String generate(String userId, String cleanPassword, String saltString) {
        String passwordToProtect = userId + ":" + cleanPassword + ":" + "42" + ":" + saltString;
        String hashString = encryptThisString(passwordToProtect);

        String protectedPassword = saltString + ":" + hashString;
        return protectedPassword;
    }

    @SneakyThrows
    public String generate(String userId, String cleanPassword) {
        String salt = salt();
        return generate(userId, cleanPassword, salt);
    }

    @Override
    public Boolean check(String userId, String cleanPassword, String protectedPassword) {
        String[] passwordObject = protectedPassword.split(":");
        if (passwordObject.length != 2) return false;

        String salt = passwordObject[0];
        String password = generate(userId, cleanPassword, salt);

        return protectedPassword.equals(password);
    }
}

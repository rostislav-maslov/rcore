package com.rcore.domain.user.port.impl;

import com.rcore.domain.user.port.PasswordGenerator;
import lombok.SneakyThrows;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class PasswordGeneratorImpl implements PasswordGenerator {

    private byte[] salt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    @SneakyThrows
    private String generate(String userId, String cleanPassword, String saltString){
        byte[] salt = saltString.getBytes();

        String passwordToProtect = userId + ":" + cleanPassword + "42";

        KeySpec keySpec = new PBEKeySpec(passwordToProtect.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(keySpec).getEncoded();
        String hashString = new String(hash);

        String protectedPassword = saltString + ":" + hashString;
        return protectedPassword;
    }

    @SneakyThrows
    public String generate(String userId, String cleanPassword){
        byte[] salt = salt();
        return generate(userId, cleanPassword, new String(salt));
    }

    @Override
    public Boolean check(String userId, String cleanPassword, String protectedPassword) {
        String[] passwordObject = protectedPassword.split(":");
        if(passwordObject.length != 2) return false;

        String salt = passwordObject[0];
        String password = generate(userId, cleanPassword, salt);

        return protectedPassword.equals(password);
    }
}

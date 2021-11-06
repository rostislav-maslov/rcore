package com.rcore.rest.api.spring.security.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

public class KeyTestsUtils {
    public static Map<String, String> generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        return Map.of(
                "privateKey", "-----BEGIN PRIVATE KEY-----\n" + Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded()) + "\n-----END PRIVATE KEY-----\n",
                "publicKey", "-----BEGIN PUBLIC KEY-----\n" + Base64.getEncoder().encodeToString(kp.getPublic().getEncoded()) + "\n-----END PUBLIC KEY-----\n");
    }
}

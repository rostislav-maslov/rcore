package com.rcore.security.infrastructure.jwt;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SecretGenerator {

    public String generate(){
        Random random = ThreadLocalRandom.current();
        byte[] randomBytes = new byte[32];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }

}

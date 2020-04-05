package com.rcore.domain.userEmailVerification.port.impl;

import com.rcore.domain.userEmailVerification.port.UserEmailVerificationCodeGenerator;

import java.util.Random;

public class UserEmailVerificationCodeGeneratorImp implements UserEmailVerificationCodeGenerator {
    @Override
    public String generate() {
        Integer min = 123456;
        Integer max = 999999;
        Random random = new Random();
        return String.valueOf(random.nextInt(max - min + 1) + min);
    }
}

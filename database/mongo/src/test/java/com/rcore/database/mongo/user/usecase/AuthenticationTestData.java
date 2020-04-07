package com.rcore.database.mongo.user.usecase;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.PasswordGenerator;

public class AuthenticationTestData {

    public static UserEntity user(String email, String pass, PasswordGenerator passwordGenerator){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(email);
        userEntity.setEmail(email);
        userEntity.setPassword(passwordGenerator.generate(email, pass));
        return userEntity;
    }

    public static UserEntity user1(PasswordGenerator passwordGenerator) {
        return user("user1", "123", passwordGenerator);
    }

    public static UserEntity user2(PasswordGenerator passwordGenerator) {
        return user("user2", "123", passwordGenerator);
    }

}
